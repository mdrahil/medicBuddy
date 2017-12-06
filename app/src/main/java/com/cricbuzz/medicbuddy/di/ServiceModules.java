package com.cricbuzz.medicbuddy.di;

import com.cricbuzz.medicbuddy.alarms.ReminderAlarmService;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Rahil on 12/6/2017.
 */
@Module
public abstract class ServiceModules {
    @ContributesAndroidInjector
    abstract ReminderAlarmService contributeReminderAlarmService();
}
