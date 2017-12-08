package com.cricbuzz.medicbuddy.ui.report;

import android.database.Observable;
import android.databinding.ObservableField;

import javax.inject.Inject;

/**
 * Created by Rahil on 12/8/2017.
 */

public class BindingModel {


    ObservableField<String> month = new ObservableField<>();
    ObservableField<String> year = new ObservableField<>();

    @Inject
    public BindingModel() {
    }
}
