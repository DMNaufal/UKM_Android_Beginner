package app_project.healthapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class EditprofileActivity extends AppCompatActivity {


    private EditText namaP,genderP,birthP,adrP;
    private ImageButton edit;
    String id_pasien , nama_pasien,alamat_pasien,tanggal_lahir,jenis_kelamin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);
        if (!SharedPrefManager.getInstance(this).isLogin()){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }
        namaP = (EditText) findViewById(R.id.tv_Name);
        genderP = (EditText) findViewById(R.id.tv_Gender);
        birthP = (EditText) findViewById(R.id.tv_Birth);
        adrP = (EditText) findViewById(R.id.tv_Address);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_Editprofile);
        setSupportActionBar(toolbar);

        namaP.setText(SharedPrefManager.getInstance(this).getNama());
        genderP.setText(SharedPrefManager.getInstance(this).getKeyJk());
        birthP.setText(SharedPrefManager.getInstance(this).getKeyTgl());
        adrP.setText(SharedPrefManager.getInstance(this).getKeyAlmt());

        edit = (ImageButton) findViewById(R.id.edit);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {

                }
        });


    }
}
