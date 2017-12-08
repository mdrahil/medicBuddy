package com.cricbuzz.medicbuddy.alarms;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.cricbuzz.medicbuddy.R;
import com.cricbuzz.medicbuddy.db.AlarmsDao;
import com.cricbuzz.medicbuddy.db.AppDb;
import com.cricbuzz.medicbuddy.db.RemindersDao;
import com.cricbuzz.medicbuddy.models.Alarms;
import com.cricbuzz.medicbuddy.models.Reminders;
import com.cricbuzz.medicbuddy.repository.common.AppExecutors;
import com.cricbuzz.medicbuddy.ui.reminders.RemindersActivity;

import java.util.Date;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import timber.log.Timber;

public class ReminderAlarmService extends Service {
    private static final String TAG = ReminderAlarmService.class.getSimpleName();

    public static final String ACTION_TRIGGER_REMINDER = "com.cricbuzz.medicbuddy.ACTION_TRIGGER_REMINDER";
    public static final String ACTION_SKIP = "com.cricbuzz.medicbuddy.ACTION_SKIP";
    public static final String ACTION_TAKEN = "com.cricbuzz.medicbuddy.ACTION_TAKEN";

    //   private static final int NOTIFICATION_ID = 42;
    private static final String EXTRA_REMINDER_ID = "extra_reminder_id";
    private static final String EXTRA_ALARM_ID = "extra_alarm_id";


    @Inject
    AppDb db;
    @Inject
    RemindersDao remindersDao;
    @Inject
    AlarmsDao alarmsDao;
    @Inject
    AppExecutors executors;
    private Ringtone ringtone;

    public static PendingIntent getReminderPendingIntent(Context context, long reminderId, long alarmId, String action) {
        Intent intent = new Intent(context, ReminderAlarmService.class);
        intent.putExtra(EXTRA_REMINDER_ID, reminderId);
        if (alarmId != 0) {
            intent.putExtra(EXTRA_ALARM_ID, alarmId);
        }
        intent.setAction(action);
        return PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    /*public ReminderAlarmService() {
        super(TAG);
    }*/

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        AndroidInjection.inject(this);

        long reminderId = intent.getLongExtra(EXTRA_REMINDER_ID, 0);
        Reminders reminder = remindersDao.loadReminder(reminderId);

        if (ACTION_TRIGGER_REMINDER.equals(intent.getAction())) {

            long alarmId = updateDb(reminder);
            playAlarmTone();

            Intent action = new Intent(this, RemindersActivity.class);
            PendingIntent operation = TaskStackBuilder.create(this)
                    .addNextIntentWithParentStack(action)
                    .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

            PendingIntent skipIntent = getReminderPendingIntent(this, reminderId, alarmId, ACTION_SKIP);

            PendingIntent takenIntent = getReminderPendingIntent(this, reminderId, alarmId, ACTION_TAKEN);

            String msg = String.format(getString(R.string.notification_body), reminder.getMedicineName(), reminder.getDosage());
            String channelId = "reminder_chanel";

            Notification notification = new NotificationCompat.Builder(this, channelId)
                    .setContentTitle(String.format(getString(R.string.take_medic), reminder.getMedicineName(), reminder.getDosage()))
                    .setContentText(msg)
                    .setSmallIcon(R.drawable.ic_alarm_notification_name)
                    .setContentIntent(operation)
                    .setAutoCancel(true)
                    .addAction(R.drawable.ic_action_skip,
                            getString(R.string.skip), skipIntent)
                    .addAction(R.drawable.ic_action_taken,
                            getString(R.string.taken), takenIntent)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText(msg))
                    .setOngoing(true)
                    .build();

            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if (manager != null) {
                // manager.notify(reminderId, notification);
            }
            startForeground((int) reminderId, notification);
        } else if (ACTION_SKIP.equals(intent.getAction())) {

            long alarmId = intent.getLongExtra(EXTRA_ALARM_ID, 0);
            updateStatus(alarmId, Alarms.STATUS_SKIPPED);
            stopAlarmTone();
            stopForeground(true);

        } else if (ACTION_TAKEN.equals(intent.getAction())) {

            long alarmId = intent.getLongExtra(EXTRA_ALARM_ID, 0);
            updateStatus(alarmId, Alarms.STATUS_TAKEN);
            stopAlarmTone();
            stopForeground(true);
        }
        return START_NOT_STICKY;
    }

    private void updateStatus(long alarmId, int statusTaken) {
        executors.diskIO().execute(() -> {
            try {
                db.beginTransaction();
                alarmsDao.updateStatus(alarmId, statusTaken, new Date().getTime());
                Timber.i("alarm id " + alarmId + " update");
                db.setTransactionSuccessful();
            } finally {
                db.endTransaction();
            }
        });
    }

    private long updateDb(Reminders reminder) {
        Alarms alarms = new Alarms();
        alarms.setReminderId(reminder.getId());
        alarms.setReminderDate(new Date());
        alarms.setActionDate(new Date());
        alarms.setStatus(Alarms.STATUS_NO_ACTION);
        alarms.setId(System.currentTimeMillis());
        executors.diskIO().execute(() -> {
            try {
                db.beginTransaction();
                long id = alarmsDao.saveAlarm(alarms);
                Timber.i("alarm id " + id + " inserted");
                db.setTransactionSuccessful();
            } finally {
                db.endTransaction();
            }

        });
        return alarms.getId();
    }

    @Override
    public void onDestroy() {
        stopAlarmTone();
        super.onDestroy();
    }

    private void stopAlarmTone() {
        if (ringtone != null)
            ringtone.stop();
    }

    private void playAlarmTone() {
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        ringtone = RingtoneManager.getRingtone(getApplicationContext(), alarmUri);
        ringtone.play();
    }
}
