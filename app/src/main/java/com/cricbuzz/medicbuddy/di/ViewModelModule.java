package com.cricbuzz.medicbuddy.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.cricbuzz.medicbuddy.ui.newReminder.NewReminderViewModel;
import com.cricbuzz.medicbuddy.ui.reminders.reminderList.ReminderListViewModel;
import com.cricbuzz.medicbuddy.ui.report.ReportViewModel;
import com.cricbuzz.medicbuddy.viewmodel.AppViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ReminderListViewModel.class)
    abstract ViewModel bindReminderListViewModel(ReminderListViewModel loginViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(NewReminderViewModel.class)
    abstract ViewModel bindNewReminderViewModel(NewReminderViewModel newReminderViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ReportViewModel.class)
    abstract ViewModel bindReportViewModel(ReportViewModel reportViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(AppViewModelFactory factory);
}
