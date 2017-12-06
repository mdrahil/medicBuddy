package com.cricbuzz.medicbuddy.ui.reminders.reminderList;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cricbuzz.medicbuddy.R;
import com.cricbuzz.medicbuddy.databinding.ItemRemindersBinding;
import com.cricbuzz.medicbuddy.models.Reminders;
import com.cricbuzz.medicbuddy.ui.common.DataBoundListAdapter;

/**
 * Created by Rahil on 12/6/2017.
 */

public class ReminderListAdapter extends DataBoundListAdapter<Reminders, ItemRemindersBinding> {


    @Override
    protected ItemRemindersBinding createBinding(ViewGroup parent) {
        return DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_reminders, parent, false);
    }

    @Override
    protected void bind(ItemRemindersBinding binding, Reminders item) {
        binding.setReminder(item);
    }

    @Override
    protected boolean areItemsTheSame(Reminders oldItem, Reminders newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    protected boolean areContentsTheSame(Reminders oldItem, Reminders newItem) {
        return oldItem.getId() == newItem.getId();
    }
}
