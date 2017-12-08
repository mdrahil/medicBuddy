package com.cricbuzz.medicbuddy.ui.reminders;


import android.content.Intent;

import com.cricbuzz.medicbuddy.R;
import com.cricbuzz.medicbuddy.base.BaseNavigationController;
import com.cricbuzz.medicbuddy.ui.newReminder.NewReminderActivity;
import com.cricbuzz.medicbuddy.ui.reminders.reminderList.ReminderListFragment;
import com.cricbuzz.medicbuddy.ui.report.ReportActivity;
import com.cricbuzz.medicbuddy.utils.Constants;

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



    public void navigateToNewReminder() {
        activity.startActivity(new Intent(activity, NewReminderActivity.class));
    }

    public void navigateToReport(long id) {
        Intent intent = new Intent(activity, ReportActivity.class);
        intent.putExtra(Constants.EXTRA_REPORT_ID,id);
        activity.startActivity(intent);
    }
}
