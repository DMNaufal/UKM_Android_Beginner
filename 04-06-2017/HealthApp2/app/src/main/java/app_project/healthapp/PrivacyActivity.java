package app_project.healthapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class PrivacyActivity extends AppCompatActivity {
    CardView cd_Changepass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_Privacy);
        setSupportActionBar(toolbar);

        cd_Changepass = (CardView) findViewById(R.id.cd_Changepass);

        cd_Changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent changepassIntent = new Intent(PrivacyActivity.this, ChangepasswordActivity.class);
                PrivacyActivity.this.startActivity(changepassIntent);
            }
        });
    }
}
