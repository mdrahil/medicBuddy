package com.cricbuzz.medicbuddy.base;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.TextView;


public abstract class BaseActivity extends AppCompatActivity {

    protected Activity mThis;
    protected String TAG;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mThis = this;
        TAG = getClass().getSimpleName();
    }

    public boolean toggleEmptyView(boolean isEmptyVisible, @IdRes int toBeHide, @IdRes int emptyView) {
        if (isEmptyVisible) {
            hideView(findViewById(toBeHide));
            showView(findViewById(emptyView));
            findViewById(emptyView).setAnimation(AnimationUtils.makeInAnimation(this, true));

        } else {
            showView(findViewById(toBeHide));
            findViewById(emptyView).setAnimation(AnimationUtils.makeOutAnimation(this, true));
        }
        return isEmptyVisible;
    }



    protected void hideView(View view) {
        if (view != null)
            view.setVisibility(View.GONE);
    }

    protected void hideView(int id) {
        if (id != 0) {
            View v = findViewById(id);
            if (v != null) v.setVisibility(View.GONE);
        }
    }

    protected void enableView(View view) {
        view.setEnabled(true);
    }

    protected void disableView(View view) {
        view.setEnabled(false);
    }

    protected void showView(View view) {
        if (view != null)
            view.setVisibility(View.VISIBLE);
    }

    protected void inivisibleView(View view) {
        if (view != null)
            view.setVisibility(View.INVISIBLE);
    }

    public String getTrimmedText(TextView textView) {
        return textView.getText().toString().trim();
    }


    protected void setStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, color));
        }
    }

    protected void setStatusTranslucent() {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            // window.setStatusBarColor(Color.TRANSPARENT);
        }
    }



}
