package com.cricbuzz.medicbuddy.ui.reminders.reminderList;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.cricbuzz.medicbuddy.models.Reminders;
import com.cricbuzz.medicbuddy.repository.repos.ReminderRepository;
import com.cricbuzz.medicbuddy.utils.SingleLiveEvent;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Rahil on 12/6/2017.
 */

public class ReminderListViewModel extends ViewModel {

    private BindingModel bindingModel;
    private ReminderRepository repository;

    private SingleLiveEvent<Reminders> reminderSavedEvent = new SingleLiveEvent<>();
    @Inject
    public ReminderListViewModel(ReminderRepository repository, BindingModel bindingModel) {
        this.bindingModel = bindingModel;
        this.repository = repository;
    }



    public BindingModel bindingModel() {
        return bindingModel;
    }

    public LiveData<List<Reminders>> loadReminders() {
        return repository.loadReminders();
    }



    public void setHasReminders(boolean hasReminders) {
        bindingModel.hasReminders.set(hasReminders);
    }
}
