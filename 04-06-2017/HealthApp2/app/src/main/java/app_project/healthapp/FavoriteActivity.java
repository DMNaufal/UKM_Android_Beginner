package app_project.healthapp;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class FavoriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        if (!SharedPrefManager.getInstance(this).isLogin()){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }
        //Bellow is Bottom Navigation
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.NavBot);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        TextView namaP = (TextView) findViewById(R.id.tvText1);
        TextView adrP = (TextView) findViewById(R.id.tvText2);

        namaP.setText(SharedPrefManager.getInstance(this).getNama());
        adrP.setText(SharedPrefManager.getInstance(this).getKeyAlmt());
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                switch (item.getItemId()) {
                    case R.id.ic_home:
                        Intent intent1 = new Intent(FavoriteActivity.this, HomeActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.ic_favorite:
                        break;
                    case R.id.ic_history:
                        Intent intent2 = new Intent(FavoriteActivity.this, HistoryActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.ic_settings:
                        Intent intent3 = new Intent(FavoriteActivity.this, SettingsActivity.class);
                        startActivity(intent3);
                        break;
                }
                return false;
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_Favorite);
        setSupportActionBar(toolbar);
    }
}
