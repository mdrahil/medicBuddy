package com.cricbuzz.medicbuddy.di;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.cricbuzz.medicbuddy.base.ActivityLifecycleCallbacksImpl;
import com.cricbuzz.medicbuddy.base.App;

import dagger.android.AndroidInjection;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Helper class to automatically inject fragments if they implement {@link Injectable}.
 */

public class AppInjector {

    public static void inject(App app) {

        DaggerAppComponent
                .builder()
                .application(app)
                .build()
                .inject(app);

        app.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacksImpl() {

            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
                super.onActivityCreated(activity, bundle);
                injectActivity(activity);
            }
        });
    }

    private static void injectActivity(Activity activity) {
        if (activity instanceof HasSupportFragmentInjector || activity instanceof Injectable) {
            AndroidInjection.inject(activity);
        }
        if (activity instanceof FragmentActivity) {
            injectFragments((FragmentActivity) activity);
        }
    }

    private static void injectFragments(FragmentActivity activity) {

        activity.getSupportFragmentManager().registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {
            @Override
            public void onFragmentCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
                if (f instanceof Injectable) {
                    AndroidSupportInjection.inject(f);
                }
            }
        }, true);
    }


}
