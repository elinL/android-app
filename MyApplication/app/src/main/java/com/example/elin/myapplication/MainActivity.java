package com.example.elin.myapplication;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    private void addTabs() {
        ActionBar bar = getSupportActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        String homeTitle = getResources().getString(R.string.home);
        ActionBar.Tab homeTab = bar.newTab();
        homeTab.setText(homeTitle);
        homeTab.setTabListener(new TabListener(this, homeTitle, HomeFragment.class));
        bar.addTab(homeTab);

        String profileTitle = getResources().getString(R.string.profile);
        ActionBar.Tab profileTab = bar.newTab();
        profileTab.setText(profileTitle);
        profileTab.setTabListener(new TabListener(this, profileTitle, ProfileFragment.class));
        bar.addTab(profileTab);

        String cameraTitle = getResources().getString(R.string.camera);
        ActionBar.Tab cameraTab = bar.newTab();
        cameraTab.setText(cameraTitle);
        cameraTab.setTabListener(new TabListener(this, cameraTitle, CameraFragment.class));
        bar.addTab(cameraTab);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        int index = getSupportActionBar().getSelectedNavigationIndex();
        outState.putInt("selected_tab_index", index);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addTabs();
        if (savedInstanceState != null) {
            int index = savedInstanceState.getInt("selected_tab_index", 0);
            getSupportActionBar().setSelectedNavigationItem(index);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_logout) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}