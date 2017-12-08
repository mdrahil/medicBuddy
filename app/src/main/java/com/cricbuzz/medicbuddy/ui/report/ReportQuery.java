package com.cricbuzz.medicbuddy.ui.report;

import java.util.Calendar;

/**
 * Created by rahil on 8/12/17.
 */

public class ReportQuery {

    String year;
    String month;
    Filter filter;

    public ReportQuery() {
        Calendar calender = Calendar.getInstance();
        year = String.valueOf(calender.get(Calendar.YEAR));
        month = String.valueOf(calender.get(Calendar.MONTH));
        filter = Filter.ALL;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }
}
