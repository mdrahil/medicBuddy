package com.cricbuzz.medicbuddy.ui.newReminder;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.cricbuzz.medicbuddy.R;
import com.cricbuzz.medicbuddy.base.BaseToolBarActivity;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class NewReminderActivity extends BaseToolBarActivity  implements HasSupportFragmentInjector {


    @Inject
    DispatchingAndroidInjector<Fragment> injector;

    @Inject
    NewReminderNavigator navigator;

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return injector;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            navigator.navigateToNewReminder();
        }
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_new_reminder;
    }

    @Override
    public String provideTitle() {
        return getString(R.string.new_reminder);
    }
}
