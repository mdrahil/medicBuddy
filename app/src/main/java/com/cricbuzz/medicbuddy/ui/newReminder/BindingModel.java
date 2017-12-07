package com.cricbuzz.medicbuddy.ui.newReminder;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.cricbuzz.medicbuddy.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Rahil on 12/6/2017.
 */

public class BindingModel {

    public final ObservableField<String> medicName = new ObservableField<>();
    public final ObservableField<String> dosage = new ObservableField<>();
    public final ObservableField<String> time = new ObservableField<>();
    public final ObservableField<String> errorMedicName = new ObservableField<>();
    public final ObservableField<String> errorDosage= new ObservableField<>();

    public final ObservableBoolean mon = new ObservableBoolean();
    public final ObservableBoolean tue = new ObservableBoolean();
    public final ObservableBoolean wed = new ObservableBoolean();
    public final ObservableBoolean thu = new ObservableBoolean();
    public final ObservableBoolean fri = new ObservableBoolean();
    public final ObservableBoolean sat = new ObservableBoolean();
    public final ObservableBoolean sun = new ObservableBoolean();

    @Inject
    public BindingModel() {
    }

    public List<String> getSelectedDays() {
        List<String> days = new ArrayList<>();
        if (mon.get()) days.add(Constants.MON_DAY);
        if (tue.get()) days.add(Constants.TUE_DAY);
        if (wed.get()) days.add(Constants.WED_DAY);
        if (thu.get()) days.add(Constants.THU_DAY);
        if (fri.get()) days.add(Constants.FRI_DAY);
        if (sat.get()) days.add(Constants.SAT_DAY);
        if (sun.get()) days.add(Constants.SUN_DAY);
        return days;
    }
}
