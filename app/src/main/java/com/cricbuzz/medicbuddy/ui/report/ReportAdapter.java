package com.cricbuzz.medicbuddy.ui.report;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cricbuzz.medicbuddy.R;
import com.cricbuzz.medicbuddy.databinding.ItemReportBinding;
import com.cricbuzz.medicbuddy.models.Alarms;
import com.cricbuzz.medicbuddy.ui.common.DataBoundListAdapter;

/**
 * Created by Rahil on 12/8/2017.
 */

public class ReportAdapter extends DataBoundListAdapter<Alarms, ItemReportBinding> {

    @Override
    protected ItemReportBinding createBinding(ViewGroup parent) {
        return DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_report, parent, false);
    }

    @Override
    protected void bind(ItemReportBinding binding, Alarms item) {
            binding.setReport(item);
    }

    @Override
    protected boolean areItemsTheSame(Alarms oldItem, Alarms newItem) {
        return oldItem.getId() == newItem.getId()
                && oldItem.getStatus() == newItem.getStatus();
    }

    @Override
    protected boolean areContentsTheSame(Alarms oldItem, Alarms newItem) {
        return oldItem.getId() == newItem.getId();
    }
}
