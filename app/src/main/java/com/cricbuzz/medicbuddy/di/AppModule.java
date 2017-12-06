package com.cricbuzz.medicbuddy.di;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.cricbuzz.medicbuddy.db.AlarmsDao;
import com.cricbuzz.medicbuddy.db.AppDb;
import com.cricbuzz.medicbuddy.db.RemindersDao;
import com.cricbuzz.medicbuddy.sharedpref.AppPreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by rahil on 16/8/17.
 */


@Module(includes = ViewModelModule.class)
public class AppModule {

    @Provides
    @Singleton
    public AppPreferenceManager providePreferenceManager(Application application) {
        return new AppPreferenceManager(application.getApplicationContext());
    }

    @Provides
    @Singleton
    AppDb provideAppDatabaseDatabase(Application application) {
        return Room.databaseBuilder(application, AppDb.class,"reminders.db").allowMainThreadQueries().build();
    }

    @Provides
    @Singleton
    RemindersDao provideRemindersDao(AppDb db) {
        return db.remindersDao();
    }
    @Provides
    @Singleton
    AlarmsDao provideAlarmsDao(AppDb db) {
        return db.alarmsDao();
    }


}
