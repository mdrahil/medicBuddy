package com.cricbuzz.medicbuddy.base;


import android.app.ProgressDialog;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.cricbuzz.medicbuddy.utils.Utils;

import java.lang.reflect.Field;


/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment implements LifecycleRegistryOwner {
    protected String TAG = "";
    private ProgressDialog mProgressDialog;
    //  protected LogInRespBean.LogInData mUserData;
    LifecycleRegistry registry = new LifecycleRegistry(this);


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();

    }




    public String getName() {
        return getClass().getSimpleName();
    }

    public String getTrimmedText(TextView textView) {
        return textView.getText().toString().trim();
    }


    @Override
    public void onDetach() {
        Utils.hideKeyboard(getContext());
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void onBackPress() {
        getActivity().onBackPressed();
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return registry;
    }


}
