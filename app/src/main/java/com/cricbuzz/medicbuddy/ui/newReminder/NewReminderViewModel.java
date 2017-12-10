package com.cricbuzz.medicbuddy.ui.newReminder;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.text.TextUtils;

import com.cricbuzz.medicbuddy.R;
import com.cricbuzz.medicbuddy.models.Reminders;
import com.cricbuzz.medicbuddy.repository.repos.ReminderRepository;
import com.cricbuzz.medicbuddy.utils.SingleLiveEvent;

import javax.inject.Inject;

/**
 * Created by Rahil on 12/6/2017.
 */

public class NewReminderViewModel extends ViewModel {

    private final Application app;
    private BindingModel bindingModel;
    private ReminderRepository repository;

    private SingleLiveEvent<Reminders> savedReminderEvent = new SingleLiveEvent<>();
    private SingleLiveEvent<String> snackbarMessage = new SingleLiveEvent<>();

    @Inject
    public NewReminderViewModel(Application app, ReminderRepository repository, BindingModel bindingModel) {
        this.app = app;
        this.bindingModel = bindingModel;
        this.repository = repository;
    }

    public void setReminder() {
        if (validData()) {
            Reminders reminder = createReminder();
            repository.saveReminders(reminder);
            savedReminderEvent.setValue(reminder);
        }
    }

    public LiveData<Reminders> reminderSavedEvent() {
        return savedReminderEvent;
    }

    public SingleLiveEvent<String> snackbarMessage(){
        return snackbarMessage;
    }

    private Reminders createReminder() {
        Reminders reminders = new Reminders();
        reminders.setMedicineName(bindingModel.medicName.get());
        reminders.setDosage(bindingModel.dosage.get());
        reminders.setTime(bindingModel.time.get());
        reminders.setDays(bindingModel.getSelectedDays());
        reminders.setId(System.currentTimeMillis()/1000);
        return reminders;
    }

    private boolean validData() {

        if (TextUtils.isEmpty(bindingModel.medicName.get())) {
            bindingModel.errorMedicName.set(app.getString(R.string.medicine_name_required));
            return false;
        } else if (TextUtils.isEmpty(bindingModel.dosage.get())) {
            bindingModel.errorDosage.set(app.getString(R.string.enter_dosage));
            return false;
        } else if (bindingModel.getSelectedDays().isEmpty()) {
            snackbarMessage.setValue(app.getString(R.string.select_day));
            return false;
        } else if (TextUtils.isEmpty(bindingModel.time.get())) {
            snackbarMessage.setValue(app.getString(R.string.set_time_alert));
            return false;
        }

        return true;
    }


    public BindingModel bindingModel() {
        return bindingModel;
    }

    public void setTime(String time) {
        bindingModel.time.set(time);
    }
}
