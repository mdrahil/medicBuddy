package com.cricbuzz.medicbuddy.sharedpref;

import android.content.Context;


/**
 * Created by rahil on 9/5/16.
 */
public class AppPreferenceManager {

    private final PreferenceUtil mPref;



    public AppPreferenceManager(Context context) {
        mPref = new PreferenceUtil(context);
    }

    public boolean isLoggedIn() {
        return mPref.getBooleanValue(PrefConstants.IS_LOGGED_IN);
    }


    public void clear() {
        mPref.clear();
    }

    public void clearAll() {
        mPref.clearAll();
    }


    public void saveUserId(String userId) {
        mPref.save(PrefConstants.USER_ID, userId);
    }
}
