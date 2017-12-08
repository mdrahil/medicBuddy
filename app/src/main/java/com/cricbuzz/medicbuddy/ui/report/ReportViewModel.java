package com.cricbuzz.medicbuddy.ui.report;

import android.arch.lifecycle.ViewModel;

import com.cricbuzz.medicbuddy.repository.repos.ReportRepository;

import javax.inject.Inject;

/**
 * Created by Rahil on 12/6/2017.
 */

public class ReportViewModel extends ViewModel {

    private BindingModel bindingModel;
    private ReportRepository repository;

    @Inject
    public ReportViewModel(ReportRepository repository, BindingModel bindingModel) {
        this.bindingModel = bindingModel;
        this.repository = repository;
    }


    public BindingModel bindingModel() {
        return bindingModel;
    }


}
