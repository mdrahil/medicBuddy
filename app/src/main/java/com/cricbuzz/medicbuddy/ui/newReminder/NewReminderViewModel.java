package com.cricbuzz.medicbuddy.ui.newReminder;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.cricbuzz.medicbuddy.models.Reminders;
import com.cricbuzz.medicbuddy.repository.repos.ReminderRepository;

import javax.inject.Inject;

/**
 * Created by Rahil on 12/6/2017.
 */

public class NewReminderViewModel extends ViewModel {

    private BindingModel bindingModel;
    private ReminderRepository repository;

    private MutableLiveData<Reminders> reminderToSet = new MutableLiveData<>();
    private LiveData<Reminders> savedReminder;
    @Inject
    public NewReminderViewModel(ReminderRepository repository,BindingModel bindingModel) {
        this.bindingModel = bindingModel;
        this.repository = repository;
        savedReminder = Transformations.switchMap(reminderToSet, repository::saveReminders);
    }

    public void setReminder() {
        if (validData()){
            Reminders reminder = createReminder();
            reminderToSet.setValue(reminder);
        }
    }

    public LiveData<Reminders> reminderSavedEvent() {
        return savedReminder;
    }

    private Reminders createReminder() {
        Reminders reminders = new Reminders();
        reminders.setMedicineName(bindingModel.medicName.get());
        reminders.setDosage(bindingModel.dosage.get());
        reminders.setTime(bindingModel.time.get());
        reminders.setDays(bindingModel.getSelectedDays());
        return reminders;
    }

    private boolean validData() {
        return true;
    }


    public BindingModel bindingModel() {
        return bindingModel;
    }

    public void setTime(String time) {
        bindingModel.time.set(time);
    }
}
