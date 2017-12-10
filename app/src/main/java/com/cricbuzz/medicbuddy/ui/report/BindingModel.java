package com.cricbuzz.medicbuddy.ui.report;

import android.databinding.ObservableField;

import javax.inject.Inject;

/**
 * Created by Rahil on 12/8/2017.
 */

public class BindingModel {


    public final ObservableField<String> month = new ObservableField<>();
    public final ObservableField<String> year = new ObservableField<>();

    @Inject
    public BindingModel() {

    }
}
