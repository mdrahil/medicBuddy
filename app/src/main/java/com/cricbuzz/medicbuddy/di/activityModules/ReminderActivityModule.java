package com.cricbuzz.medicbuddy.di.activityModules;

import com.cricbuzz.medicbuddy.ui.reminders.reminderList.ReminderListFragment;
import com.cricbuzz.medicbuddy.ui.reminders.report.ReportFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by rahil on 24/8/17.
 */

@Module
public abstract class ReminderActivityModule {

    @ContributesAndroidInjector
    abstract ReminderListFragment reminderListingFragment();

    @ContributesAndroidInjector
    abstract ReportFragment reportFragment();


}
