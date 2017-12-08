package com.cricbuzz.medicbuddy.ui.reminders;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.cricbuzz.medicbuddy.R;
import com.cricbuzz.medicbuddy.base.BaseToolBarActivity;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class RemindersActivity extends BaseToolBarActivity implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> injector;

    @Inject
    ReminderNavigator navigator;

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return injector;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            navigator.navigateToReminderList();
        }

        findViewById(R.id.fab).setOnClickListener(view ->
                navigator.navigateToNewReminder());
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_home;
    }


    @Override
    public String provideTitle() {
        return getString(R.string.app_name);
    }
}
