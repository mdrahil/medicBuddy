package com.cricbuzz.medicbuddy.ui.reminders;


import android.content.Intent;

import com.cricbuzz.medicbuddy.R;
import com.cricbuzz.medicbuddy.base.BaseNavigationController;
import com.cricbuzz.medicbuddy.ui.newReminder.NewReminderActivity;
import com.cricbuzz.medicbuddy.ui.reminders.reminderList.ReminderListFragment;
import com.cricbuzz.medicbuddy.ui.report.ReportFragment;

import javax.inject.Inject;

/**
 * Created by Rahil on 12/06/2017.
 */

public class ReminderNavigator extends BaseNavigationController {

    @Inject
    public ReminderNavigator(RemindersActivity activity) {
        super(activity);
    }

    void navigateToReminderList() {
        fragmentManagerHelper().replaceFragment(R.id.container, ReminderListFragment.newInstance());
    }

    public void navigateToSkippedMedicines() {
        fragmentManagerHelper().replaceFragment(R.id.container, ReportFragment.newInstance(ReportFragment.STATUS_SKIPPED));
    }

    public void navigateToTakenMedicines() {
        fragmentManagerHelper().replaceFragment(R.id.container, ReportFragment.newInstance(ReportFragment.STATUS_TAKEN));
    }

    public void navigateToNewReminder() {
        activity.startActivity(new Intent(activity, NewReminderActivity.class));
    }
}
