package com.example.bdrx.docappc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    //Button for Login - LoginPage
    @BindView(R.id.btn_login)
        Button btn_Login;

    //TextView with OnClick - LoginPage
    @BindView(R.id.text_fpass_login)
        TextView text_fpass_login;
    @BindView(R.id.text_signup_login)
        TextView text_signup_login;

    //Special String - LoginPage
    @BindString(R.string.text_forgot)
        String text_forgot;
    @BindString(R.string.text_signUp)
        String text_signUp;

    //Common Funcion - LoginPage
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        //Special String
        text_fpass_login.setText(Html.fromHtml(text_forgot));
        text_signup_login.setText(Html.fromHtml(text_signUp));
    }

    //Button for Login
    @OnClick(R.id.btn_login)
    public void btn_login_mtd(View v) {
        intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    //TextView with OnClick
    @OnClick(R.id.text_fpass_login)
    public void text_fpass_login_mtd(View v){
        intent = new Intent(LoginActivity.this, ForgetPassActivity.class);
        startActivity(intent);
    }

    //TextView with OnClick
    @OnClick(R.id.text_signup_login)
    public void text_signup_login_mtd(View v){
        intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }
}
