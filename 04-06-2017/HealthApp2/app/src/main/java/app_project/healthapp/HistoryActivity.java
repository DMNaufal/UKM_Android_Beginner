package app_project.healthapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        if (!SharedPrefManager.getInstance(this).isLogin()){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.NavBot);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                switch (item.getItemId()) {
                    case R.id.ic_home:
                        Intent intent1 = new Intent(HistoryActivity.this, HomeActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.ic_favorite:
                        Intent intent2 = new Intent(HistoryActivity.this, FavoriteActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.ic_history:
                        break;
                    case R.id.ic_settings:
                        Intent intent3 = new Intent(HistoryActivity.this, SettingsActivity.class);
                        startActivity(intent3);
                        break;
                }
                return false;
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_History);
        setSupportActionBar(toolbar);
    }
}
