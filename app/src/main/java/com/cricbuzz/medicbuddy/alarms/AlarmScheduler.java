package com.cricbuzz.medicbuddy.alarms;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;

import com.cricbuzz.medicbuddy.models.Reminders;
import com.cricbuzz.medicbuddy.utils.DateUtils;

/**
 * Helper to manage scheduling the reminder alarm
 */
public class AlarmScheduler {


    public static void scheduleAlarm(Context context, Reminders reminders) {
        AlarmManager manager =(AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        PendingIntent operation =
                ReminderAlarmService.getReminderPendingIntent(context, reminders.getId(), ReminderAlarmService.ACTION_TRIGGER_REMINDER);

        for (String day :
                reminders.getDays()) {

            int dayOfWeek = DateUtils.getDayOfWeek(day);
            if (dayOfWeek != 0) {
                long alarmTime = DateUtils.getAlarmTime(dayOfWeek, reminders.getTime());
                if (manager != null) {
                    manager.setRepeating(AlarmManager.RTC_WAKEUP, alarmTime, 24 * 60 * 60 * 1000, operation);
                }
            }
        }
    }




}
