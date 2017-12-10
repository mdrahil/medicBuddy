package com.cricbuzz.medicbuddy.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.List;

/**
 * Created by rahil on 18/1/16.
 */
public class Utils {

    /**
     * Method to show keyboard
     *
     * @param context Context of the calling activity
     */
    public static void showKeyboard(Context context) {
        try {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(((Activity) context).getCurrentFocus(), InputMethodManager.SHOW_IMPLICIT);
        } catch (Exception e) {

        }
    }

    public static DatePickerDialog getBirthDatePicker(Context context, DatePickerDialog.OnDateSetListener callback) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, callback, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());


        return datePickerDialog;
    }

    /**
     * Method to show keyboard
     *
     * @param context  Context of the calling activity
     * @param editText Edittext which will receive focus
     */
    public static void showKeyboard(Context context, EditText editText) {
        showKeyboard(context);
        try {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
            imm.showSoftInput(((Activity) context).getCurrentFocus(), InputMethodManager.SHOW_FORCED);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void modifyDialogBounds(Dialog dialog) {
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = dialog.getWindow();
        lp.copyFrom(window.getAttributes());
        //This makes the dialog take up the full width
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.width = (int) (dialog.getContext().getResources().getDisplayMetrics().widthPixels * 0.8);
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT/*(int) (dialog.getContext().getResources().getDisplayMetrics().heightPixels * 0.35)*/;
        window.setAttributes(lp);
    }




    public static void hideView(View view) {
        if (view != null)
            view.setVisibility(View.GONE);
    }

    public static void showView(View view) {
        if (view != null)
            view.setVisibility(View.VISIBLE);
    }


    public static void invisibleView(View v) {
        v.setVisibility(View.INVISIBLE);
    }


    /**
     * Method to hide keyboard
     *
     * @param mContext Context of the calling class
     */
    public static void hideKeyboard(Context mContext) {
        try {
            InputMethodManager inputManager = (InputMethodManager) mContext
                    .getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(((Activity) mContext).getCurrentFocus().getWindowToken(), 0);
        } catch (Exception ignored) {
        }
    }


    public static void hideKeyboard(View editText) {
        hideKeyboard(editText.getContext());
        try {
            InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        } catch (Exception ignored) {
        }
    }


    public static boolean isServiceRunning(Context context, Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }


    public static void copyBean(Object source, Object target) {

        Field[] fields = source.getClass().getDeclaredFields();
        for (Field sourceField :
                fields) {
            System.out.println(fields.length);
            try {
                sourceField.setAccessible(true);
                Field targetField = target.getClass().getDeclaredField(sourceField.getName());
                if (targetField != null) {
                    targetField.setAccessible(true);
                    Object value2 = sourceField.get(source);
                    targetField.set(target, value2);
                }

            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isValidList(List list) {
        return list != null && !list.isEmpty();
    }

    public static Spanned fromHtml(String source) {
        if (source == null || source.isEmpty()) return null;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N)
            return Html.fromHtml(source, Html.FROM_HTML_MODE_COMPACT);
        else
            return Html.fromHtml(source);
    }


    public static String concatString(String... strings){
        return TextUtils.join(" ",strings);
    }




    public static boolean isValidString(String s) {
        return s!= null && !s.isEmpty();
    }
}

