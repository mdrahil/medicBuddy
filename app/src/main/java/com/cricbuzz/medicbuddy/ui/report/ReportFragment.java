package com.cricbuzz.medicbuddy.ui.report;


import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cricbuzz.medicbuddy.R;
import com.cricbuzz.medicbuddy.base.BaseFragment;
import com.cricbuzz.medicbuddy.databinding.FragmentReportBinding;
import com.cricbuzz.medicbuddy.di.Injectable;
import com.cricbuzz.medicbuddy.viewmodel.AppViewModelFactory;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReportFragment extends BaseFragment implements Injectable, EventHandlers {

    public static final String EXTRA_REPORT_TYPE = "extra_report_type";
    public static final int STATUS_PENDING = 0;
    public static final int STATUS_TAKEN = 1;
    public static final int STATUS_SKIPPED = 2;
    private FragmentReportBinding mBinding;

    @Inject
    AppViewModelFactory viewModelFactory;
    private ReportViewModel mViewModel;
    private ReportAdapter mAdapter;

    public ReportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_report, container, false);
        return mBinding.getRoot();
    }


    public static Fragment newInstance() {
        Bundle bundle = new Bundle();
        Fragment fragment = new ReportFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(ReportViewModel.class);
        mBinding.setModel(mViewModel.bindingModel());
        mBinding.setClickHandler(this);
        mBinding.rvReport.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.rvReport.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mAdapter = new ReportAdapter();
        mBinding.rvReport.setAdapter(mAdapter);
    }

    @Override
    public void selectMonth() {

    }

    @Override
    public void selectYear() {

    }
}
