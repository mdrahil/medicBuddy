package com.cricbuzz.medicbuddy.ui.newReminder;


import android.app.TimePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

import com.cricbuzz.medicbuddy.R;
import com.cricbuzz.medicbuddy.alarms.AlarmScheduler;
import com.cricbuzz.medicbuddy.base.BaseFragment;
import com.cricbuzz.medicbuddy.databinding.FragmentNewReminderBinding;
import com.cricbuzz.medicbuddy.di.Injectable;
import com.cricbuzz.medicbuddy.utils.DateUtils;
import com.cricbuzz.medicbuddy.utils.DialogUtil;
import com.cricbuzz.medicbuddy.utils.Utils;
import com.cricbuzz.medicbuddy.viewmodel.AppViewModelFactory;

import java.util.Calendar;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewReminderFragment extends BaseFragment implements Injectable, EventHandlers, TimePickerDialog.OnTimeSetListener {


    @Inject
    AppViewModelFactory viewModelFactory;
    private FragmentNewReminderBinding mBinding;
    private NewReminderViewModel mViewModel;
    private TimePickerDialog mTimePickerDialog;

    public NewReminderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_reminder, container, false);
        return mBinding.getRoot();
    }

    public static Fragment newInstance() {
        return new NewReminderFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(NewReminderViewModel.class);
        mBinding.setHandler(this);
        mBinding.setModel(mViewModel.bindingModel());


        mViewModel.snackbarMessage().observe(this, msg -> {
            Utils.hideKeyboard(getContext());
            DialogUtil.showSnackBar(mBinding.getRoot(), msg);
        });

        mViewModel.reminderSavedEvent().observe(this, reminders -> {
            String msg = getString(R.string.reminder_saved) + " " + reminders.getMedicineName();
            AlarmScheduler.scheduleAlarm(getContext(), reminders);
            DialogUtil.showToast(getContext(), msg);
            Timber.i(msg);
            getActivity().finish();
        });
    }

    @Override
    public void setTime() {
        if (mTimePickerDialog == null) {
            Calendar cal = Calendar.getInstance();
            mTimePickerDialog = new TimePickerDialog(getContext(), this,
                    cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), false);
        }
        mTimePickerDialog.show();
    }

    @Override
    public void setReminder() {
        mViewModel.setReminder();

    }

    @Override
    public void onDayClicked() {
        Utils.hideKeyboard(getContext());
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int min) {

        mViewModel.setTime(DateUtils.getTimeFromTimePicker(hour, min));
    }
}
