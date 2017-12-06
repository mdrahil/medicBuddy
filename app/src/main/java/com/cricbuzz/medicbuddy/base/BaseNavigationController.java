package com.cricbuzz.medicbuddy.base;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Rahil on 8/20/2017.
 */

public class BaseNavigationController {


    protected FragmentActivity activity;
    private FragmentManagerHelper fragmentManagerHelper;

    public BaseNavigationController(FragmentActivity activity) {
        this.activity = activity;
    }

     protected FragmentManagerHelper fragmentManagerHelper() {
        if (fragmentManagerHelper == null) {
            fragmentManagerHelper = new FragmentManagerHelper(activity);
        }
        return fragmentManagerHelper;
    }

    protected Activity activity() {
        return activity;
    }


    protected Intent provideIntent(Class clazz){

        return new Intent(activity,clazz);

    }





}
