package com.cricbuzz.medicbuddy.base;

import android.support.annotation.AnimRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.cricbuzz.medicbuddy.R;


/**
 * Created by rahil on 18/8/17.
 */

public class FragmentManagerHelper  {

    private FragmentManager fragmentManager;
    FragmentActivity activity;


    public FragmentManagerHelper(FragmentActivity activity) {
        this.activity = activity;
        this.fragmentManager = activity.getSupportFragmentManager();
    }

    public void addFragment(Fragment fragment, boolean isAddToBackStack) {
        addFragment(fragment, R.id.container, isAddToBackStack, 0,
                0,
                0,
                0);
    }

    public void addFragment(Fragment fragment, int container, boolean isAddToBackStack) {
        addFragment(fragment, container, isAddToBackStack, 0,
                0,
                0,
                0);
    }

    public void hideFragment(Class<? extends Fragment> fragment, @AnimRes int enter,
                             @AnimRes int exit, @AnimRes int popEnter, @AnimRes int popExit) {

        Fragment frag = activity.getSupportFragmentManager().findFragmentByTag(fragment.getSimpleName());
        if (frag != null)
            activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(enter,
                    exit,
                    popEnter,
                    popExit).hide(frag).commit();
    }

    public void hideFragment(Class<? extends Fragment> fragment) {
        hideFragment(fragment, 0,
                0,
                0,
                0);
    }

    public void showFragment(Class<? extends Fragment> fragment, @AnimRes int enter,
                             @AnimRes int exit, @AnimRes int popEnter, @AnimRes int popExit) {
        Fragment frag = activity.getSupportFragmentManager().findFragmentByTag(fragment.getSimpleName());
        if (frag != null) {
            activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(enter,
                    exit, popEnter, popExit).addToBackStack(fragment.getSimpleName()).show(frag).commit();
        }
    }


    public void showFragment(Class<? extends Fragment> fragment) {
        if (activity.isFinishing() /*|| isDestroyed()*/) return;
        try {
            Fragment frag = activity.getSupportFragmentManager().findFragmentByTag(fragment.getSimpleName());
            if (frag != null)
                activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(0,
                        0,
                        0,
                        0).show(frag).commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addFragment(Fragment fragment, int containerId, boolean isAddToBackStack, @AnimRes int enter,
                            @AnimRes int exit, @AnimRes int popEnter, @AnimRes int popExit) {


        if (activity.isFinishing() /*|| isDestroyed()*/) return;
        String fragmentName = fragment.getClass().getSimpleName();
        if (isAddToBackStack) {

            //  popLastFragment();
            fragmentManager
                    .beginTransaction()
                    .setCustomAnimations(enter,
                            exit, popEnter, popExit)
                    .add(containerId, fragment, fragmentName)
                    .addToBackStack(fragmentName)
                    .commitAllowingStateLoss();
        } else {
            fragmentManager
                    .beginTransaction()
                    .setCustomAnimations(enter,
                            exit, popEnter, popExit)
                    .add(containerId, fragment, fragmentName)
                    .commitAllowingStateLoss();
        }
    }


    public Fragment getFragmentByTag(Class<? extends Fragment> fragment) {
        return fragmentManager.findFragmentByTag(fragment.getSimpleName());
    }


    public void replaceFragment(int containerId, Fragment fragment) {

        fragmentManager
                .beginTransaction()
                .replace(containerId, fragment, fragment.getClass()
                        .getSimpleName())
                .commit();
    }

    public void popLastFragment() {

        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        }
    }
    //protected abstract FragmentActivity getActivity();
}
