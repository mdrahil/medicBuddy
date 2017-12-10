package com.cricbuzz.medicbuddy.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.cricbuzz.medicbuddy.models.Alarms;

import java.util.List;


/**
 * Created by Rahil on 12/6/2017.
 */

@Dao
public interface AlarmsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long saveAlarm(Alarms reminders);

    @Query("SELECT * FROM Alarms WHERE reminderId = :reminderId AND reminderDate  between :fromTimeInMilis AND :toInMilis")
    LiveData<List<Alarms>> loadAlarms(long reminderId, long fromTimeInMilis, long toInMilis);

    @Query("SELECT * FROM Alarms WHERE reminderId = :reminderId AND  reminderDate  BETWEEN :fromTimeInMilis AND :toInMilis AND status = :status")
    LiveData<List<Alarms>> loadAlarms(long reminderId, long fromTimeInMilis, long toInMilis, int status);

    @Query("UPDATE Alarms SET status = :statusTaken,actionDate = :time WHERE id = :alarmId")
    void updateStatus(long alarmId, int statusTaken, long time);
}
