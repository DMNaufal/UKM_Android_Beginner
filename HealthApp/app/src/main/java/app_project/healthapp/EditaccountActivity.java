package app_project.healthapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import app_project.healthapp.Fragment.SettingsFragment;

public class EditaccountActivity extends AppCompatActivity {

    ImageButton btnClose;
    TextView btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editaccount);

        btnClose = (ImageButton)findViewById(R.id.ic_close_edit_account);
        btnUpdate = (TextView)findViewById(R.id.update_profile);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditaccountActivity.this, MainActivity.class);
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
