package com.cricbuzz.medicbuddy.base;

import android.app.Activity;
import android.app.Application;
import android.app.Service;

import com.cricbuzz.medicbuddy.BuildConfig;
import com.cricbuzz.medicbuddy.di.AppInjector;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasServiceInjector;
import timber.log.Timber;

/**
 * Created by rahil on 16/8/17.
 */

public class App extends Application implements HasActivityInjector,HasServiceInjector {



    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Inject
    DispatchingAndroidInjector<Service> dispatchingServiceAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        AppInjector.inject(this);
        if (BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }
    }


    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    public AndroidInjector<Service> serviceInjector() {
        return dispatchingServiceAndroidInjector;
    }
}