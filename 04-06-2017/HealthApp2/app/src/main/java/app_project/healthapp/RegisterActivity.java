package app_project.healthapp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    ImageButton ib_Backarrow;
    TextView tv_Submit;
    private EditText email,nama,alamat,pass;
    private TextView tanggallahir;
    private Spinner jeniskelaminl;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ///Below Spinner
        Spinner sGender = (Spinner) findViewById(R.id.spGender);
        ArrayAdapter<String> sAdapter = new ArrayAdapter<String>(RegisterActivity.this,
                android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.gender));
        sAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sGender.setAdapter(sAdapter);
        email = (EditText) findViewById(R.id.etEmail);
        nama = (EditText) findViewById(R.id.etName);
        pass = (EditText) findViewById(R.id.etPassword);
        tanggallahir = (TextView) findViewById(R.id.tvBirth);
        jeniskelaminl = (Spinner) findViewById(R.id.spGender);
        alamat = (EditText) findViewById(R.id.etAddress);
        progressDialog = new ProgressDialog(this);

        ib_Backarrow = (ImageButton)findViewById(R.id.ib_Backarrow);
        tv_Submit = (TextView) findViewById(R.id.tv_Submit);

        ib_Backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                RegisterActivity.this.startActivity(backIntent);
            }
        });

        tv_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == tv_Submit) {
                    resgiterUser();
                }
            }
        });
    }
    private void resgiterUser() {
        final String nama_pasien = nama.getText().toString().trim();
        final String email_pasien = email.getText().toString().trim();
        final String password = pass.getText().toString().trim();
        final String tanggal_lahir = tanggallahir.getText().toString().trim();
        final String jenis_kelamin = jeniskelaminl.getSelectedItem().toString().trim();
        final String alamat_pasien = alamat.getText().toString().trim();
        progressDialog.setMessage("Register Pasien....");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_REGISTER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplication(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(getApplication(), error.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("nama_pasien", nama_pasien);
                params.put("email_pasien", email_pasien);
                params.put("password",password);
                params.put("tanggal_lahir", tanggal_lahir);
                params.put("jenis_kelamin", jenis_kelamin);
                params.put("alamat_pasien", alamat_pasien);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
    ///Below is for Birth Button
    public void datePicker(View view){
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(), "date");
    }

    private void setDate(final Calendar calendar) {
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        ((TextView) findViewById(R.id.tvBirth)).setText(dateFormat.format(calendar.getTime()));
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar cal = new GregorianCalendar(year, month, day);
        setDate(cal);
    }

    public static class DatePickerFragment extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);
        }
    }

}
