package com.cricbuzz.medicbuddy.di.activityModules;

import com.cricbuzz.medicbuddy.ui.newReminder.NewReminderFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by rahil on 24/8/17.
 */

@Module
public abstract class NewReminderActivityModule {

    @ContributesAndroidInjector
    abstract NewReminderFragment newReminderFragment();



}
