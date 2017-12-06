package com.cricbuzz.medicbuddy.ui.newReminder;


import com.cricbuzz.medicbuddy.R;
import com.cricbuzz.medicbuddy.base.BaseNavigationController;

import javax.inject.Inject;

/**
 * Created by Rahil on 9/10/2017.
 */

public class NewReminderNavigator extends BaseNavigationController {

    @Inject
    public NewReminderNavigator(NewReminderActivity activity) {
        super(activity);
    }

    void navigateToNewReminder() {
        fragmentManagerHelper().replaceFragment(R.id.container, NewReminderFragment.newInstance());
    }

}
