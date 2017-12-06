package com.cricbuzz.medicbuddy.ui.reminders;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_skipped) {
            navigator.navigateToSkippedMedicines();
            return true;
        } else if (id == R.id.action_taken) {
            navigator.navigateToTakenMedicines();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public String provideTitle() {
        return getString(R.string.app_name);
    }
}
