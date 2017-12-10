package com.cricbuzz.medicbuddy.ui.report;


import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListPopupWindow;

import com.cricbuzz.medicbuddy.R;
import com.cricbuzz.medicbuddy.base.BaseFragment;
import com.cricbuzz.medicbuddy.databinding.FragmentReportBinding;
import com.cricbuzz.medicbuddy.di.Injectable;
import com.cricbuzz.medicbuddy.utils.DialogUtil;
import com.cricbuzz.medicbuddy.viewmodel.AppViewModelFactory;

import javax.inject.Inject;

import static com.cricbuzz.medicbuddy.utils.Constants.EXTRA_REPORT_ID;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReportFragment extends BaseFragment implements Injectable, EventHandlers {


    public static final int STATUS_PENDING = 0;
    public static final int STATUS_TAKEN = 1;
    public static final int STATUS_SKIPPED = 2;
    private FragmentReportBinding mBinding;

    @Inject
    AppViewModelFactory viewModelFactory;
    private ReportViewModel mViewModel;
    private ReportAdapter mAdapter;
    private ListPopupWindow mMothPopupWindow;
    private ListPopupWindow mYearPopupWindow;

    public ReportFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_report, container, false);
        return mBinding.getRoot();
    }


    public static Fragment newInstance(long reminderId) {
        Bundle bundle = new Bundle();
        bundle.putLong(EXTRA_REPORT_ID, reminderId);
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

        mViewModel.reminder().observe(this, reminders -> {
            if (reminders != null) {
                mBinding.setReminder(reminders);
            }
        });
        Bundle bundle = getArguments();
        if (bundle.containsKey(EXTRA_REPORT_ID)) {
            long reminderId = bundle.getLong(EXTRA_REPORT_ID);
            mViewModel.setReminderId(reminderId);
        }
        mViewModel.reports().observe(this, alarms -> mAdapter.replace(alarms));

        mViewModel.loadReport();
    }

    @Override
    public void selectMonth() {

        if (mMothPopupWindow == null) {
            mMothPopupWindow = DialogUtil.createPopupFromResource(getContext(), R.array.months);
            mMothPopupWindow.setAnchorView(mBinding.tvMonth);
            mMothPopupWindow.setOnItemClickListener((adapterView, view, pos, l) -> {
                mViewModel.setSelectedMonth(pos);
                mMothPopupWindow.dismiss();
            });
        }
        if (mYearPopupWindow != null && mYearPopupWindow.isShowing()) mYearPopupWindow.dismiss();

       if (!mMothPopupWindow.isShowing())mMothPopupWindow.show();
    }

    @Override
    public void selectYear() {

        if (mYearPopupWindow == null) {
            mYearPopupWindow = DialogUtil.createYearPopup(getContext(), 5);
            mYearPopupWindow.setAnchorView(mBinding.tvYear);
            mYearPopupWindow.setOnItemClickListener((adapterView, view, pos, l) -> {
                mViewModel.setSelectedYear(adapterView.getItemAtPosition(pos).toString());
                mYearPopupWindow.dismiss();
            });
        }
        if (mMothPopupWindow != null && mMothPopupWindow.isShowing()) mMothPopupWindow.dismiss();
        if (!mYearPopupWindow.isShowing()) mYearPopupWindow.show();

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_home, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_show_all:
                mViewModel.filterReport(Filter.ALL);
                return true;

            case R.id.action_taken:
                mViewModel.filterReport(Filter.TAKEN);
                return true;

            case R.id.action_skipped:
                mViewModel.filterReport(Filter.SKIPPED);
                return true;

           /* case R.id.action_not_taken:
                mViewModel.filterReport(Filter.SKIPPED);
                return true;*/

        }

        return false;
    }
}
