package com.cricbuzz.medicbuddy.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.cricbuzz.medicbuddy.db.DateConverters;

import java.util.Date;

/**
 * Created by Rahil on 12/7/2017.
 */
@Entity(foreignKeys = @ForeignKey(entity = Reminders.class,
        parentColumns = "id",
        childColumns = "reminderId"))
@TypeConverters(DateConverters.class)
public class Alarms {
    public long getReminderId() {
        return reminderId;
    }

    public void setReminderId(long reminderId) {
        this.reminderId = reminderId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getReminderDate() {
        return reminderDate;
    }

    public void setReminderDate(Date reminderDate) {
        this.reminderDate = reminderDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    public static final int STATUS_NO_ACTION = 0;
    public static final int STATUS_SKIPPED = 1;
    public static final int STATUS_TAKEN = 2;
    private long reminderId;
    @PrimaryKey
    private long id;
    private Date reminderDate;
    private int status = STATUS_NO_ACTION;
    private Date actionDate;
}
