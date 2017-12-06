package com.cricbuzz.medicbuddy.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import timber.log.Timber;


/**
 * Created by rahil on 18/8/17.
 */

public class ChildFragmentManagerHelper extends FragmentManagerHelper {

    private static final String TAG = ChildFragmentManagerHelper.class.getSimpleName();
    private FragmentManager fragmentManager;
    FragmentActivity activity;


    public ChildFragmentManagerHelper(FragmentActivity activity, Fragment fragment) {
        super(activity);
        this.activity = activity;
        this.fragmentManager = fragment.getChildFragmentManager();
    }

    protected void replaceChildFragment(int containerId, Fragment fragment) {
        if (fragment.getActivity() != null) {
            fragmentManager.beginTransaction()
                    .replace(containerId, fragment, fragment.getClass().getSimpleName()).commit();
        }
    }

    protected void removeChildFragment(Class<? extends Fragment> clazz) {
        String fragmentName = clazz.getSimpleName();
        Fragment fragment = fragmentManager.findFragmentByTag(fragmentName);
        fragmentManager.beginTransaction().remove(fragment).commitAllowingStateLoss();
    }

    protected void removeChildFragment(String tag) {
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment != null)
            fragmentManager.beginTransaction().remove(fragment).commit();
        else
            Timber.e(TAG, "Fragment with tag : " + tag + " not found");
    }

    protected void removeChildFragment(int containerId) {
        Fragment fragment = fragmentManager.findFragmentById(containerId);
        if (fragment != null)
            fragmentManager.beginTransaction().remove(fragment).commit();
        else
            Timber.e(TAG, "Fragment not found");
    }
}
