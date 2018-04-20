package app_project.healthapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class DatadiriActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datadiri);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_Datadiri);
        setSupportActionBar(toolbar);
    }
}
