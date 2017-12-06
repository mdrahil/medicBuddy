package com.cricbuzz.medicbuddy.utils;

import android.arch.lifecycle.MutableLiveData;


public class AbsentMutableLiveData extends MutableLiveData {
    private AbsentMutableLiveData() {
        postValue(null);
    }

    public static <T> MutableLiveData<T> create() {
        //noinspection unchecked
        return new AbsentMutableLiveData();
    }


}
