package app_project.healthapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ChangepasswordActivity extends AppCompatActivity {

    ImageButton btnClose;
    TextView btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);

        btnClose = (ImageButton)findViewById(R.id.ic_close_changepassword);
        btnUpdate = (TextView)findViewById(R.id.update_profile);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangepasswordActivity.this, PrivacyActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Data diperbarui",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
