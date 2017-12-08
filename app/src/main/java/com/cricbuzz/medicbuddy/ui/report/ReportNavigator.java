package com.cricbuzz.medicbuddy.ui.report;


import com.cricbuzz.medicbuddy.R;
import com.cricbuzz.medicbuddy.base.BaseNavigationController;

import javax.inject.Inject;

/**
 * Created by Rahil on 12/06/2017.
 */

public class ReportNavigator extends BaseNavigationController {

    @Inject
    public ReportNavigator(ReportActivity activity) {
        super(activity);
    }

    void navigateToReport() {
        fragmentManagerHelper().replaceFragment(R.id.container, ReportFragment.newInstance());
    }


}
