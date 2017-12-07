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

    public void saveReminders(Reminders reminder) {
        executors.diskIO().execute(() -> {
            long id = remindersDao.saveReminder(reminder);
            Timber.i("reminder id " + id + " inserted");

        });
    }

    public LiveData<List<Reminders>> loadReminders() {
        return remindersDao.loadReminders();
    }
}
