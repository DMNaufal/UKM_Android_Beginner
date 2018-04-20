package com.example.bdrx.docappc;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetPassActivity extends AppCompatActivity {

    //Button
    @BindView(R.id.btn_submit_FogetPass)
        Button btn_submit_FogetPass;

    //String
    @BindString(R.string.toast_confirm)
        String toast_confirm;

    //Logo Package---------------------------
    //ImageView Logo = tempat berdirinya Logo
    @BindView(R.id.logo_heart_FogetPass)
        ImageView logo_heart_FogetPass;

    //Drawable Logo = Logo yang bakal berdiri
    @BindDrawable(R.drawable.heart)
        Drawable heart;
    //End -----------------------------------

    //Common
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);

        ButterKnife.bind(this);

        //Drawable Logo
        logo_heart_FogetPass.setImageDrawable(heart);
    }

    //Button
    @OnClick(R.id.btn_submit_FogetPass)
    public void btn_submit_FogetPass_mtd(View v){
        intent = new Intent(ForgetPassActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), toast_confirm, Toast.LENGTH_LONG).show();
    }
}
