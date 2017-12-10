package com.cricbuzz.medicbuddy.ui.report;

import java.util.Calendar;

/**
 * Created by rahil on 8/12/17.
 */

public class ReportQuery {

    private Filter filter;
    private long reminderId;

    public long getReminderId() {
        return reminderId;
    }

    public void setReminderId(long reminderId) {
        this.reminderId = reminderId;
    }

    private Calendar calender = Calendar.getInstance();

    public ReportQuery() {

        calender.set(Calendar.DAY_OF_MONTH, 1);
        calender.set(Calendar.HOUR_OF_DAY, 0);
        calender.set(Calendar.MINUTE, 0);
        calender.set(Calendar.SECOND, 0);
        calender.set(Calendar.MILLISECOND, 0);
        filter = Filter.ALL;
    }


    public void setYear(String year) {

        try {
            this.calender.set(Calendar.YEAR, Integer.parseInt(year));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }


    public void setMonth(int month) {
        try {
            this.calender.set(Calendar.MONTH, month);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public long getFromTime() {

        return calender.getTimeInMillis();
    }

    public long getToTime() {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(this.calender.getTimeInMillis());
        c.add(Calendar.MONTH, 1);
        c.add(Calendar.MILLISECOND, -1);
        return c.getTimeInMillis();
    }
}
