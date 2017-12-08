package com.cricbuzz.medicbuddy.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListPopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.cricbuzz.medicbuddy.R;

import java.util.Calendar;


/**
 * Created by Rahil on 8/9/15.
 */
public class DialogUtil {

    private static final String TAG = DialogUtil.class.getSimpleName();


    public static void showProgressDialog(@NonNull ProgressDialog progressDialog) {
        try {
            if (progressDialog != null) {
                Activity activity = (Activity) progressDialog.getContext();
                if (activity != null && !activity.isFinishing()) {
                    if (!progressDialog.isShowing()) progressDialog.show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void hideProgressDialog(@NonNull ProgressDialog progressDialog) {
        try {
            if (progressDialog.isShowing()) progressDialog.dismiss();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static void showToast(@NonNull Context context, String msg) {
        Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    public static void showToast(@NonNull Context context, int msg) {
        Toast.makeText(context.getApplicationContext(), context.getResources().getString(msg), Toast.LENGTH_LONG).show();
    }


    public static android.support.v7.app.AlertDialog getListDialog(Context context, int itemsRes, String title, final DialogInterface.OnClickListener listener) {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context/*, R.style.AppDialog*/);
        builder.setTitle(title);
        builder.setItems(itemsRes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (listener != null) listener.onClick(dialog, which);
            }
        });
        return builder.create();
    }


    public static android.support.v7.app.AlertDialog getOkCancelDialog(Context context, int msg, int style, final DialogInterface.OnClickListener listener) {
        return getOkCancelDialog(context, msg, style, "Ok", "Cancel", listener);
    }

    public static android.support.v7.app.AlertDialog getOkCancelDialog(Context context, int msg, int style, String positiveBtn, String negetiveBtn, final DialogInterface.OnClickListener listener) {
        //android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this, R.style.Theme_AppCompat_Dialog_Alert);
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context, style);
        builder.setCancelable(true);
        builder.setTitle(context.getString(R.string.app_name));
        builder.setNegativeButton(positiveBtn, null);
        builder.setPositiveButton(negetiveBtn, listener);

        builder.setMessage(msg);
        //builder.setCustomTitle()
        final android.support.v7.app.AlertDialog alertDialog = builder.create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(final DialogInterface dialog) {

                Button b = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                b.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        // TODO Do something
                        listener.onClick(dialog, 1);

                    }
                });
            }
        });
        return alertDialog;
    }

    public static android.support.v7.app.AlertDialog getOkCancelDialog(final Context context, String msg, String yesText, String noText, AlertDialog.OnClickListener yesListner) {

        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context/*, R.style.AppDialog*/);
        builder.setMessage(msg);
        builder.setTitle(context.getString(R.string.app_name));
        builder.setPositiveButton(yesText, yesListner);
        builder.setNegativeButton(noText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        final android.support.v7.app.AlertDialog alert = builder.create();
        alert.setMessage(msg);
        return builder.create();

    }


    public static android.support.v7.app.AlertDialog showOkCancelDialog(Context context, String message, String ok, String cancel, final DialogInterface.OnClickListener dialogListener) {
        android.support.v7.app.AlertDialog dialog = null;
        if (context != null && context instanceof Activity && !((Activity) context).isFinishing()) {
            final android.support.v7.app.AlertDialog.Builder builder =
                    new android.support.v7.app.AlertDialog.Builder(context);
            builder.setMessage(message);
            builder.setCancelable(false);
            builder.setPositiveButton(ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogListener.onClick(dialogInterface, i);
                    dialogInterface.cancel();
                }
            });
            builder.setNegativeButton(cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    dialogInterface.cancel();
                }
            });
            dialog = builder.create();
            builder.show();


        }
        return dialog;
    }


    public static void showOkDialog(Context context, String title, String message) {
        if (context != null && context instanceof Activity && !((Activity) context).isFinishing()) {

            final android.support.v7.app.AlertDialog.Builder builder =
                    new android.support.v7.app.AlertDialog.Builder(context);

            if (!TextUtils.isEmpty(title))
                builder.setTitle(title);
            builder.setMessage(message);
            builder.setPositiveButton(context.getString(android.R.string.ok), null);

            builder.show();
        }
    }

    public static void invisibleView(View v) {
        v.setVisibility(View.INVISIBLE);
    }


    public static void closeDialogFragment(DialogFragment dialogFragment) {
        if (dialogFragment != null) dialogFragment.dismiss();
    }

    public static Snackbar showSnackBar(View anyView, String msg) {
        //Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_LONG).show();
        final Snackbar snackBar = Snackbar.make(anyView, msg, Snackbar.LENGTH_LONG);
        snackBar.setActionTextColor(Color.WHITE);
        View view = snackBar.getView();
        view.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.colorPrimaryDark));
        TextView tv = (TextView)
                view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);
        tv.setMaxLines(5);
        snackBar.setAction(R.string.dismiss, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackBar.dismiss();
            }
        });
        snackBar.show();
        return snackBar;
    }

    public static Snackbar showSnackBar(View anyView, int msg) {
        //Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_LONG).show();
        Resources res = anyView.getContext().getResources();
        return showSnackBar(anyView, res.getString(msg));
    }

    public static ListPopupWindow createPopupFromResource(Context context, int stringRes) {

        String[] months = context.getResources().getStringArray(stringRes);
        ListPopupWindow popupWindow = new ListPopupWindow(context);
        popupWindow.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, months));
        return popupWindow;

    }


    public static ListPopupWindow createYearPopup(Context context, int noYears) {
        Calendar calender = Calendar.getInstance();
        int currentYear = calender.get(Calendar.YEAR);
        String[] years = new String[noYears];
        for (int i = 0; i < noYears; i++) {
            years[i] = String.valueOf(currentYear - i);
        }
        ListPopupWindow popupWindow = new ListPopupWindow(context);
        popupWindow.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, years));
        return popupWindow;
    }
}
