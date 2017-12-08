package com.cricbuzz.medicbuddy.ui.report;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.cricbuzz.medicbuddy.models.Alarms;
import com.cricbuzz.medicbuddy.models.Reminders;
import com.cricbuzz.medicbuddy.repository.repos.ReminderRepository;
import com.cricbuzz.medicbuddy.repository.repos.ReportRepository;
import com.cricbuzz.medicbuddy.utils.AbsentLiveData;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Rahil on 12/6/2017.
 */

public class ReportViewModel extends ViewModel {

    private ReminderRepository reminderRepository;
    private BindingModel bindingModel;
    private ReportRepository reportRepository;

    private LiveData<Reminders> reminder;
    private final MutableLiveData<Long> reminderId = new MutableLiveData<>();
    private LiveData<List<Alarms>> report;
    private MutableLiveData<ReportQuery> reportQuery = new MutableLiveData<>();

    @Inject
    public ReportViewModel(ReminderRepository reminderRepository, ReportRepository repository, BindingModel bindingModel) {
        this.bindingModel = bindingModel;
        this.reportRepository = repository;
        this.reminderRepository = reminderRepository;
        this.reminder = Transformations.switchMap(reminderId, reminderId -> {
            if (reminderId == null || reminderId == 0) {
                return AbsentLiveData.create();
            }
            return reminderRepository.loadReminder(reminderId);
        });

        this.report = Transformations.switchMap(reportQuery, reportRepository::loadReport);

    }


    public BindingModel bindingModel() {
        return bindingModel;
    }


    public LiveData<Reminders> reminder() {
        return reminder;
    }

    public void setReminderId(long id) {
        reminderId.setValue(id);
    }

    public void setSelectedMonth(String month) {
        ReportQuery query = reportQuery.getValue();
        if (query == null) {
            query = new ReportQuery();
            query.setYear(month);
        }
        reportQuery.setValue(query);
        bindingModel.month.set(month);
    }

    public void setSelectedYear(String year) {

        ReportQuery query = reportQuery.getValue();
        if (query == null) {
            query = new ReportQuery();
            query.setYear(year);
        }
        reportQuery.setValue(query);
        bindingModel.year.set(year);
    }

    public void filterReport(Filter filter) {
        ReportQuery query = reportQuery.getValue();
        if (query == null) {
            query = new ReportQuery();
            query.setFilter(filter);
        }
        reportQuery.setValue(query);
    }

    public LiveData<List<Alarms>> reports() {
        return report;
    }

    public void loadReport() {
        reportQuery.setValue(new ReportQuery());
    }
}
