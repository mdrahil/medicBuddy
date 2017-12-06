package com.cricbuzz.medicbuddy.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.cricbuzz.medicbuddy.models.Alarms;
import com.cricbuzz.medicbuddy.models.Reminders;


/**
 * Created by rahil on 28/8/17.
 */

@Database(entities = {Reminders.class, Alarms.class}, version = 1)
public abstract class AppDb extends RoomDatabase {

    public abstract RemindersDao remindersDao();
    public abstract AlarmsDao alarmsDao();
}
