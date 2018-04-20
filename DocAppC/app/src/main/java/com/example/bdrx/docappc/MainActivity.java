package com.example.bdrx.docappc;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.bdrx.docappc.Fragment.FavoriteFragment;
import com.example.bdrx.docappc.Fragment.HistoryFragment;
import com.example.bdrx.docappc.Fragment.HomeFragment;
import com.example.bdrx.docappc.Fragment.SettingsFragment;
import com.example.bdrx.docappc.Helper.BottomNavigationHelper;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameL_mainActivity, HomeFragment.newInstance())
                    .commit();
        }

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navbar_mainActivity);
        com.example.bdrx.docappc.Helper.BottomNavigationHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_home:
                HomeFragment homefrag = new HomeFragment();
                getSupportFragmentManager()
                        .beginTransaction().replace(R.id.frameL_mainActivity,
                        homefrag,
                        homefrag.getTag()).commit();
                return true;

            case R.id.navigation_favorite:
                FavoriteFragment favfrag = new FavoriteFragment();
                getSupportFragmentManager()
                        .beginTransaction().replace(R.id.frameL_mainActivity,
                        favfrag,
                        favfrag.getTag()).commit();
                return true;

            case R.id.navigation_history:
                HistoryFragment hisfrag = new HistoryFragment();
                getSupportFragmentManager()
                        .beginTransaction().replace(R.id.frameL_mainActivity,
                        hisfrag,
                        hisfrag.getTag()).commit();
                return true;

            case R.id.navigation_account:
                SettingsFragment setfrag = new SettingsFragment();
                getSupportFragmentManager()
                        .beginTransaction().replace(R.id.frameL_mainActivity,
                        setfrag,
                        setfrag.getTag()).commit();
                return true;
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameL_mainActivity, fragment)
                .commit();

        return false;
    }

}
