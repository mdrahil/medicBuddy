package com.cricbuzz.medicbuddy.di;

import com.cricbuzz.medicbuddy.di.activityModules.NewReminderActivityModule;
import com.cricbuzz.medicbuddy.di.activityModules.ReminderActivityModule;
import com.cricbuzz.medicbuddy.di.activityModules.ReportActivityModule;
import com.cricbuzz.medicbuddy.ui.newReminder.NewReminderActivity;
import com.cricbuzz.medicbuddy.ui.reminders.RemindersActivity;
import com.cricbuzz.medicbuddy.ui.report.ReportActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by rahil on 16/8/17.
 */

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = NewReminderActivityModule.class)
    abstract NewReminderActivity contributeNewReminderActivity();

    @ContributesAndroidInjector(modules = ReminderActivityModule.class)
    abstract RemindersActivity contributeRemindersActivity();

    @ContributesAndroidInjector(modules = ReportActivityModule.class)
    abstract ReportActivity contributeReportActivity();

}
