package app_project.healthapp;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.Calendar;

public class SignUpActivity extends AppCompatActivity {

    MaterialBetterSpinner materialBetterSpinner ;
    String date;
    Calendar calender = Calendar.getInstance();
    EditText edittext;
    Button btn;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Spinner (Dropdown) //
        materialBetterSpinner = (MaterialBetterSpinner)findViewById(R.id.material_spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SignUpActivity.this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.gender));
        materialBetterSpinner.setAdapter(adapter);
        // End //

        // Date Picker //
        edittext = (EditText)findViewById(R.id.etDate);
        edittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(SignUpActivity.this, R.style.datepicker, listener,
                        calender.get(Calendar.YEAR),calender.get(Calendar.MONTH),calender.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        // End //

        // Alert Dialog //
        btn = (Button)findViewById(R.id.submitBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertB = new AlertDialog.Builder(SignUpActivity.this, R.style.alertDiag);

                // Title Dialog
                alertB.setTitle("Kebijakan Privasi");

                //Message Dialog
                alertB.setMessage("Klik tombol Accept untuk melanjutkan");

                // "Accept" Button
                alertB.setPositiveButton("ACCEPT", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {

                        intent = new Intent(SignUpActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Cek Email anda untuk melakukan konfirmasi",
                                Toast.LENGTH_LONG).show();

                    }
                });

                // "Decline" Button
                alertB.setNegativeButton("DECLINE", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                });

                // Showing Alert Message
                alertB.show();
            }
        });
        // End //

    }

    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            edittext.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
        }
    };
}
