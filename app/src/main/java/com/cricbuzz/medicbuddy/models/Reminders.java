package com.cricbuzz.medicbuddy.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.cricbuzz.medicbuddy.db.DayTypeConverters;

import java.util.List;

/**
 * Created by Rahil on 12/6/2017.
 */

@Entity
@TypeConverters(DayTypeConverters.class)
public class Reminders {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String medicineName;
    private List<String> days;
    private String dosage;
    private String time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public List<String> getDays() {
        return days;
    }

    public void setDays(List<String> days) {
        this.days = days;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public static boolean isReminderFor(List<String> days, String day) {
        if (day == null || days == null) return false;
        return days.contains(day);
    }
}
