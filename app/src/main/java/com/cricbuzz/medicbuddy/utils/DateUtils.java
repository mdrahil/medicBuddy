package com.cricbuzz.medicbuddy.utils;

import android.app.TimePickerDialog;
import android.content.Context;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by rahil on 30/8/17.
 */

public class DateUtils {


    public static TimePickerDialog getTimePickerDialog(Context context, TimePickerDialog.OnTimeSetListener listener) {
        Calendar currentTime = Calendar.getInstance();
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        int minute = currentTime.get(Calendar.MINUTE);
        return new TimePickerDialog(context, listener, hour, minute, false);
    }

    /*  public static String getTimeFromTimePicker(int selectedHour, int selectedMinute) {
          String aMpM = "AM";
          if (selectedHour > 11) {
              aMpM = "PM";
          }
          //Make the 24 hour time format to 12 hour time format
          int currentHour;
          if (selectedHour > 11)
              currentHour = selectedHour - 12;
          else
              currentHour = selectedHour;
          if (currentHour == 0) currentHour = 12;

          String time = currentHour + ":" + String.format(Locale.getDefault(), "%02d", selectedMinute) + " " + aMpM;
          return time;
      }*/
    public static String getTimeFromTimePicker(int selectedHour, int selectedMinute) {

        final String time = selectedHour + ":" + selectedMinute;

        try {
            final SimpleDateFormat sdf = new SimpleDateFormat("H:mm", Locale.getDefault());
            final Date dateObj = sdf.parse(time);
            return new SimpleDateFormat(Constants.TIME_FORMAT, Locale.getDefault()).format(dateObj);
        } catch (final ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getDate(long timeStamp) {
        try {
            DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
            Date netDate = (new Date(timeStamp));
            return sdf.format(netDate);
        } catch (Exception ex) {
            return "";
        }
    }
    public static long getAlarmTime(int dayOfWeek, String time) {
        // Add this day of the week line to your existing code
        try {
            Date date = new SimpleDateFormat(Constants.TIME_FORMAT, Locale.getDefault()).parse(time);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_WEEK,dayOfWeek);
            return calendar.getTimeInMillis();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int getDayOfWeek(String day) {

        switch (day){
            case Constants.SUN_DAY:
                return 1;
            case Constants.MON_DAY:
                return 2;
            case Constants.TUE_DAY:
                return 3;
            case Constants.WED_DAY:
                return 4;
            case Constants.THU_DAY:
                return 5;
            case Constants.FRI_DAY:
                return 6;
            case Constants.SAT_DAY:
                return 7;
        }
        return 0;
    }
    public static String getTime(){
        return new SimpleDateFormat(Constants.TIME_FORMAT, Locale.getDefault()).format(new Date());
    }
}
