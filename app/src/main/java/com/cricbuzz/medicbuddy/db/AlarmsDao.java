package com.cricbuzz.medicbuddy.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.cricbuzz.medicbuddy.models.Alarms;

import java.util.Date;
import java.util.List;


/**
 * Created by Rahil on 12/6/2017.
 */

@Dao
public interface AlarmsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long saveAlarm(Alarms reminders);

    @Query("SELECT * FROM Alarms")
    LiveData<List<Alarms>> loadAlarms();

    @Query("UPDATE Alarms SET status = :statusTaken,actionDate = :time where id = :alarmId")
    void updateStatus(long alarmId, int statusTaken, long time);
}
