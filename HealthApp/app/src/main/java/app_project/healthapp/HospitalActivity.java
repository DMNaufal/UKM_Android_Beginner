package app_project.healthapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import app_project.healthapp.Fragment.HistoryFragment;
//import app_project.healthapp.Fragment.HomeFragment;

public class HospitalActivity extends AppCompatActivity {

    FloatingActionButton fb_book;

    ImageButton ib_back;

    private static final String TAG = "HospitalActivity";

    private SectionPageAdapter mSectionPageAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);
        Log.d(TAG, "onCreate: Starting.");

        mSectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        ib_back = (ImageButton)findViewById(R.id.ib_back);
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HospitalActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        fb_book = (FloatingActionButton) findViewById(R.id.fb_book);
        fb_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bookIntent = new Intent(HospitalActivity.this, DatadiriActivity.class);
                HospitalActivity.this.startActivity(bookIntent);
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new TabDetail(), "DETAIL");
        adapter.addFragment(new TabList(), "LIST DOCTOR");
        viewPager.setAdapter(adapter);
    }
}
