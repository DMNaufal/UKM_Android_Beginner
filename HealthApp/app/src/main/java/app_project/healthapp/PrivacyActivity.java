package app_project.healthapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import app_project.healthapp.Fragment.SettingsFragment;

public class PrivacyActivity extends AppCompatActivity {

    CardView cd_Changepass;
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);

        cd_Changepass = (CardView) findViewById(R.id.cd_Changepass);

        cd_Changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent changepassIntent = new Intent(PrivacyActivity.this, ChangepasswordActivity.class);
                PrivacyActivity.this.startActivity(changepassIntent);
            }
        });

        btnBack = (ImageButton)findViewById(R.id.ic_back_privacy);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrivacyActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}
