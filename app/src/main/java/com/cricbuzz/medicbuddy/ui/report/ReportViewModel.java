package com.cricbuzz.medicbuddy.ui.report;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.cricbuzz.medicbuddy.R;
import com.cricbuzz.medicbuddy.models.Alarms;
import com.cricbuzz.medicbuddy.models.Reminders;
import com.cricbuzz.medicbuddy.repository.repos.ReminderRepository;
import com.cricbuzz.medicbuddy.repository.repos.ReportRepository;
import com.cricbuzz.medicbuddy.utils.AbsentLiveData;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Rahil on 12/6/2017.
 */

public class ReportViewModel extends ViewModel {

    private final String[] monthsNames;
    private ReminderRepository reminderRepository;
    private BindingModel bindingModel;
    private ReportRepository reportRepository;

    private LiveData<Reminders> reminder;
    private final MutableLiveData<Long> reminderId = new MutableLiveData<>();
    private LiveData<List<Alarms>> report;
    private MutableLiveData<ReportQuery> reportQuery = new MutableLiveData<>();

    @Inject
    public ReportViewModel(Application app, ReminderRepository reminderRepository, ReportRepository repository, BindingModel bindingModel) {
        this.bindingModel = bindingModel;
        this.reportRepository = repository;
        this.reminderRepository = reminderRepository;

        //set initial month and time
        Calendar calendar = Calendar.getInstance();
        this.monthsNames = app.getResources().getStringArray(R.array.months);

        String currentMonth = monthsNames[calendar.get(Calendar.MONTH)];
        this.bindingModel.month.set(currentMonth);
        int year = calendar.get(Calendar.YEAR);
        this.bindingModel.year.set(String.valueOf(year));

        this.reminder = Transformations.switchMap(reminderId, reminderId -> {
            if (reminderId == null || reminderId == 0) {
                return AbsentLiveData.create();
            }
            return reminderRepository.loadReminder(reminderId);
        });

        this.report = Transformations.switchMap(reportQuery, reportRepository::loadReport);

    }


    BindingModel bindingModel() {
        return bindingModel;
    }


    public LiveData<Reminders> reminder() {
        return reminder;
    }

    void setReminderId(long id) {
        reminderId.setValue(id);

    }

    void setSelectedMonth(int month) {
        ReportQuery query = reportQuery.getValue();
        query.setMonth(month);
        reportQuery.setValue(query);
        bindingModel.month.set(monthsNames[month]);
    }

    void setSelectedYear(String year) {

        ReportQuery query = reportQuery.getValue();
        query.setYear(year);
        reportQuery.setValue(query);
        bindingModel.year.set(year);
    }

    void filterReport(Filter filter) {
        ReportQuery query = reportQuery.getValue();
        query.setFilter(filter);
        reportQuery.setValue(query);
    }

    LiveData<List<Alarms>> reports() {
        return report;
    }

    void loadReport() {
        ReportQuery query = reportQuery.getValue();
        if (query == null) {
            query = new ReportQuery();
            query.setReminderId(reminderId.getValue());
        }
        reportQuery.setValue(query);
    }
}
