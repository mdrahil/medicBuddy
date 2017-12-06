package com.cricbuzz.medicbuddy.repository.repos;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.cricbuzz.medicbuddy.db.AppDb;
import com.cricbuzz.medicbuddy.db.RemindersDao;
import com.cricbuzz.medicbuddy.models.Reminders;
import com.cricbuzz.medicbuddy.repository.common.AppExecutors;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by Rahil on 12/6/2017.
 */

public class ReminderRepository {

    private AppDb db;
    private AppExecutors executors;
    private RemindersDao remindersDao;


    @Inject
    public ReminderRepository(AppDb db, RemindersDao remindersDao, AppExecutors executors) {
        this.db = db;
        this.executors = executors;
        this.remindersDao = remindersDao;
    }

    public MutableLiveData<Reminders> saveReminders(Reminders reminder) {
        MutableLiveData<Reminders> remindersMutableLiveData = new MutableLiveData<>();
        executors.diskIO().execute(() -> {
            long id = remindersDao.saveReminder(reminder);
            Timber.i("reminder id " + id + " inserted");
            reminder.setId((int) id);
            executors.mainThread().execute(() -> remindersMutableLiveData.setValue(reminder));

        });
        return remindersMutableLiveData;
    }

    public LiveData<List<Reminders>> loadReminders() {
        return remindersDao.loadReminders();
    }
}
