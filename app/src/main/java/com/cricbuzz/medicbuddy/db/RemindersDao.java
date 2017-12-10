package com.cricbuzz.medicbuddy.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.cricbuzz.medicbuddy.models.Reminders;

import java.util.List;


/**
 * Created by Rahil on 12/6/2017.
 */

@Dao
public interface RemindersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long saveReminder(Reminders reminders);

    @Query("SELECT * FROM Reminders ORDER BY id DESC")
    LiveData<List<Reminders>> loadReminders();

    @Query("SELECT * FROM Reminders where id = :reminderId")
    Reminders loadReminder(long reminderId);

    @Query("SELECT * FROM Reminders where id = :reminderId")
    LiveData<Reminders> loadReminderAsLiveData(long reminderId);
}
