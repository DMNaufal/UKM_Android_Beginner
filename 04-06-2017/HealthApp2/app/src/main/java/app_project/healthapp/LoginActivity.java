package app_project.healthapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends Activity {


    private EditText etemail, etpassword;
    private Button bLogin;
    private  TextView tvRegisterHere;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (SharedPrefManager.getInstance(this).isLogin()){
            finish();
            startActivity(new Intent(this, HomeActivity.class));
            return;
        }
        etemail = (EditText) findViewById(R.id.etEmail);
        etpassword = (EditText) findViewById(R.id.etPassword);

        bLogin = (Button) findViewById(R.id.bLogin);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon Tunggu...");
        tvRegisterHere = (TextView) findViewById(R.id.tvRegister);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == bLogin){
                   // pasinLogin();
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                }
            }
        });

        tvRegisterHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });
    }

    private void pasinLogin() {
        final String email_pasien = etemail.getText().toString().trim();
        final String password = etpassword.getText().toString().trim();
        progressDialog.show();
        StringRequest stringrequest = new StringRequest(
                Request.Method.POST, Constants.URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject obj = new JSONObject(response);
                    if (!obj.getBoolean("error")){
                        SharedPrefManager.getInstance(getApplicationContext())
                                .pasienLogin(
                                        obj.getInt("id_pasien"),
                                        obj.getString("nama_pasien"),
                                        obj.getString("email_pasien"),
                                        obj.getString("tanggal_lahir"),
                                        obj.getString("jenis_kelamin"),
                                        obj.getString("alamat_pasien")
                                );    Toast.makeText(getApplicationContext(),("sukses"),Toast.LENGTH_LONG).show();
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        finish();

                    }else {
                        Toast.makeText(getApplicationContext(),obj.getString("message"),Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params = new HashMap<>();
                params.put("email_pasien", email_pasien);
                params.put("password", password);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringrequest);
    }

    public void startRegister(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}
