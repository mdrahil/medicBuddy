package com.cricbuzz.medicbuddy.ui.reminders.reminderList;


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
import com.cricbuzz.medicbuddy.databinding.FragmentReminderListBinding;
import com.cricbuzz.medicbuddy.di.Injectable;
import com.cricbuzz.medicbuddy.viewmodel.AppViewModelFactory;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReminderListFragment extends BaseFragment implements Injectable {


    private FragmentReminderListBinding mBinding;
    @Inject
    AppViewModelFactory viewModelFactory;
    private ReminderListViewModel mViewModel;
    private ReminderListAdapter mAdapter;

    public ReminderListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_reminder_list, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(ReminderListViewModel.class);
        mBinding.setModel(mViewModel.bindingModel());

        mBinding.rvReminders.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.rvReminders.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mAdapter = new ReminderListAdapter();
        mBinding.rvReminders.setAdapter(mAdapter);

        mViewModel.loadReminders().observe(this, reminders -> {
            mViewModel.setHasReminders(reminders != null && !reminders.isEmpty());
            mAdapter.replace(reminders);

        });
    }

    public static Fragment newInstance() {
        return new ReminderListFragment();
    }
}
