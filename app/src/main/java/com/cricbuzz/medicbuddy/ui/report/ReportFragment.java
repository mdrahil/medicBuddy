package com.cricbuzz.medicbuddy.ui.report;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cricbuzz.medicbuddy.R;
import com.cricbuzz.medicbuddy.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReportFragment extends BaseFragment {

    public static final String EXTRA_REPORT_TYPE = "extra_report_type";
    public static final int STATUS_PENDING = 0;
    public static final int STATUS_TAKEN = 1;
    public static final int STATUS_SKIPPED = 2;
    public ReportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_report, container, false);
    }


    public static Fragment newInstance() {
        Bundle bundle = new Bundle();
        Fragment fragment = new ReportFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
