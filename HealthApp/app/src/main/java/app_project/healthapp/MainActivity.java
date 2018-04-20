package app_project.healthapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import app_project.healthapp.Fragment.FavoriteFragment;
import app_project.healthapp.Fragment.HistoryFragment;
import app_project.healthapp.Fragment.HomeFragment;
import app_project.healthapp.Fragment.SettingsFragment;
import app_project.healthapp.Helper.BottomNavigationHelper;

public class MainActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.FrameCon, HomeFragment.newInstance())
                    .commit();
        }

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navbar);
        BottomNavigationHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

    }
    
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_home:
                HomeFragment homefrag = new HomeFragment();
                getSupportFragmentManager()
                        .beginTransaction().replace(R.id.FrameCon,
                        homefrag,
                        homefrag.getTag()).commit();
                return true;

            case R.id.navigation_favorite:
                FavoriteFragment favfrag = new FavoriteFragment();
                getSupportFragmentManager()
                        .beginTransaction().replace(R.id.FrameCon,
                        favfrag,
                        favfrag.getTag()).commit();
                return true;

            case R.id.navigation_history:
                HistoryFragment hisfrag = new HistoryFragment();
                getSupportFragmentManager()
                        .beginTransaction().replace(R.id.FrameCon,
                        hisfrag,
                        hisfrag.getTag()).commit();
                return true;

            case R.id.navigation_account:
                SettingsFragment setfrag = new SettingsFragment();
                getSupportFragmentManager()
                        .beginTransaction().replace(R.id.FrameCon,
                        setfrag,
                        setfrag.getTag()).commit();
                return true;
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.FrameCon, fragment)
                .commit();

        return false;
    }

}
