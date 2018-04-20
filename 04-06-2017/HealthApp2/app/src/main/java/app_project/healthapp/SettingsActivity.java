package app_project.healthapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class SettingsActivity extends AppCompatActivity {
    CardView cd_EditProfile, cd_EditAccount, cd_Privacy, cd_Help, cd_About;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        if (!SharedPrefManager.getInstance(this).isLogin()){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }
        //Bellow is Bottom Navigation
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.NavBot);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                switch (item.getItemId()) {
                    case R.id.ic_home:
                        Intent intent1 = new Intent(SettingsActivity.this, HomeActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.ic_favorite:
                        Intent intent2 = new Intent(SettingsActivity.this, FavoriteActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.ic_history:
                        Intent intent3 = new Intent(SettingsActivity.this, HistoryActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.ic_settings:
                        break;
                }
                return false;
            }
        });

        //Bottom is CardView
        cd_EditProfile = (CardView) findViewById(R.id.cd_EditProfile);
        cd_EditAccount = (CardView) findViewById(R.id.cd_EditAccount);
        cd_Privacy = (CardView) findViewById(R.id.cd_Privacy);
        cd_Help = (CardView) findViewById(R.id.cd_Help);
        cd_About = (CardView) findViewById(R.id.cd_About);

        cd_EditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == cd_EditProfile) {

                    Intent profileIntent = new Intent(SettingsActivity.this, EditprofileActivity.class);
                    SettingsActivity.this.startActivity(profileIntent);
                }
            }
        });

        cd_EditAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent accountIntent = new Intent(SettingsActivity.this, EditaccountActivity.class);
                SettingsActivity.this.startActivity(accountIntent);
            }
        });

        cd_Privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent privacyIntent = new Intent(SettingsActivity.this, PrivacyActivity.class);
                SettingsActivity.this.startActivity(privacyIntent);
            }
        });

        cd_Help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent helpIntent = new Intent(SettingsActivity.this, HelpActivity.class);
                SettingsActivity.this.startActivity(helpIntent);
            }
        });

        cd_About.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aboutIntent = new Intent(SettingsActivity.this, AboutActivity.class);
                SettingsActivity.this.startActivity(aboutIntent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.bLogout:
                SharedPrefManager.getInstance(this).logout();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }
        return true;
    }
}
