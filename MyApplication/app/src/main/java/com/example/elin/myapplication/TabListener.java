package com.example.elin.myapplication;


import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;


/**
 * Created by elin on 2015-04-22.
 */
public class TabListener implements ActionBar.TabListener {

    private final Activity mActivity;
    private final String mTag;
    private final Class mFragmentClass;
    private Fragment mFragment;

    public TabListener(FragmentActivity activity, String tag, Class fragmentClass) {
        mActivity = activity;
        mTag = tag;
        mFragmentClass = fragmentClass;
        mFragment = activity.getSupportFragmentManager().findFragmentByTag(tag);
    }


    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        if (mFragment == null) {
            mFragment = Fragment.instantiate(mActivity, mFragmentClass.getName());
            ft.replace(android.R.id.content, mFragment, mTag);
        } else {
            ft.attach(mFragment);
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        if (mFragment != null) {
            ft.detach(mFragment);
        }
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}


