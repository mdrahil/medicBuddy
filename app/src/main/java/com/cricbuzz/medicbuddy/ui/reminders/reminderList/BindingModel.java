package com.cricbuzz.medicbuddy.ui.reminders.reminderList;

import android.databinding.ObservableBoolean;

import javax.inject.Inject;

/**
 * Created by Rahil on 12/6/2017.
 */

public class BindingModel {

    public final ObservableBoolean hasReminders = new ObservableBoolean();
    @Inject
    public BindingModel() {
    }
}
