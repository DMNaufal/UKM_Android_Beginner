package app_project.healthapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    Button bLogin;
    TextView textV, textP;
    Intent intent;
    View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Sting
        textV = (TextView)findViewById(R.id.txtSignup);
        textV.setText(Html.fromHtml(getString(R.string.signUp)));

        textV = (TextView)findViewById(R.id.txtForgotP);
        textV.setText(Html.fromHtml(getString(R.string.txtForgot)));
        //End Sting

        bLogin = (Button) findViewById(R.id.btn_login);
        textV = (TextView)findViewById(R.id.txtSignup);
        textP = (TextView)findViewById(R.id.txtForgotP);

        bLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                intent = new Intent(LoginActivity.this, MainActivity.class);
                LoginActivity.this.startActivity(intent);
                finish();
            }
        });

        textV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(LoginActivity.this, SignUpActivity.class);
                LoginActivity.this.startActivity(intent);
            }
        });

        textP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(LoginActivity.this, ForgetPassActivity.class);
                LoginActivity.this.startActivity(intent);
            }
        });
    }

}
