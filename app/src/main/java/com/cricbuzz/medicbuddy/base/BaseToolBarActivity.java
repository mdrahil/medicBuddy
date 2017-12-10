package com.cricbuzz.medicbuddy.base;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.MenuRes;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.cricbuzz.medicbuddy.R;

import timber.log.Timber;


/**
 * Created by Rahil on 12/06/2017.
 * Base class for the activities which has simple toolbar with simple home icon or with back_icon/exit icon
 */
public abstract class BaseToolBarActivity extends BaseActivity implements TitleProvider {

    protected Toolbar mToolbar;

    /**
     * base method to integrate toolbar with the activity
     *
     * @param title         string for title
     * @param homeIconResId drawable resource for home icon
     */
    protected void setUpToolbar(String title, @DrawableRes int homeIconResId) {
        mToolbar = findViewById(R.id.tool_bar);
        if (mToolbar != null) {
            //mToolbar.setTitle(title);
            setTitle(title);
            if (homeIconResId != 0)
                mToolbar.setNavigationIcon(homeIconResId);
            else
                mToolbar.setNavigationIcon(null);
        } else {
            Timber.e(TAG, "can not find toolbar ");
        }
        setSupportActionBar(mToolbar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * method to integrate toolbar with the activity
     *
     * @param titleId       string resources id for title
     * @param homeIconResId drawable resource for home icon
     */
    protected void setUpToolbar(int titleId, @DrawableRes int homeIconResId) {
        setUpToolbar(getString(titleId), homeIconResId);
    }

    protected void setUpToolbar(@StringRes int titleId) {
        setUpToolbar(getString(titleId), R.drawable.ic_back);
    }


    protected void setUpToolbar(String title) {
        setUpToolbar(title, R.drawable.ic_back);
    }


    protected void setUpToolbar(@StringRes int titleResId, @DrawableRes int backIconResId, boolean isBackEnabled, @MenuRes int menu, Toolbar.OnMenuItemClickListener listener) {
        setUpToolbar(getString(titleResId), backIconResId);
        mToolbar.inflateMenu(menu);
        mToolbar.setOnMenuItemClickListener(listener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutId = provideLayoutId();
        if (layoutId != 0) {
            setContentView(layoutId);
            setUpToolbar(provideTitle());
        }
        getSupportFragmentManager().addOnBackStackChangedListener(this::setActionBarTitle);
    }

    protected abstract int provideLayoutId();


    private void setActionBarTitle() {
        FragmentManager manager = getSupportFragmentManager();
        int count = manager.getBackStackEntryCount();
        if (count > 0) {
            String tag = manager.getBackStackEntryAt(count - 1).getName();
            Fragment fragment = manager.findFragmentByTag(tag);
            if (fragment instanceof TitleProvider) {
                String title = ((TitleProvider) fragment).provideTitle();
                setTitle(title);
            } else {
                setTitle(provideTitle());
            }
        } else {
            setTitle(provideTitle());
        }
    }

    @Override
    public void onBackPressed() {
        //setActionBarTitle();
        super.onBackPressed();


    }
}
