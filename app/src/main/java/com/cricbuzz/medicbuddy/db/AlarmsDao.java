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

    @Query("SELECT * FROM Alarms")
    LiveData<List<Alarms>> loadAlarms();


}
