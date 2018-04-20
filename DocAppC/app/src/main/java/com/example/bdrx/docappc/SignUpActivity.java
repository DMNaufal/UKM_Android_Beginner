package com.example.bdrx.docappc;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import butterknife.BindArray;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class SignUpActivity extends AppCompatActivity {

    //TextInput
    @BindView(R.id.in_name_signUp)
        TextInputEditText in_name_signUp;
    @BindView(R.id.in_email_signUp)
        TextInputEditText in_email_signUp;
    @BindView(R.id.in_address_signUp)
        TextInputEditText in_address_signUp;
    @BindView(R.id.in_password_signUp)
        TextInputEditText in_password_signUp;
    @BindView(R.id.in_RePassword_signUp)
        TextInputEditText in_RePassword_signUp;
    @BindView(R.id.in_date_signUp)
        TextInputEditText in_date_signUp;
    //------------------------------------------

    //ButtonSubmit
    @BindView(R.id.btn_submit_signUp)
        Button btn_submit_signUp;

    //String - Check string.xml to change
    @BindString(R.string.dialog_privacy)
        String dialog_privacy;
    @BindString(R.string.dialog_coPrivacy)
        String dialog_coPrivacy;
    @BindString(R.string.text_accept)
        String text_accept;
    @BindString(R.string.text_decline)
        String text_decline;
    @BindString(R.string.toast_confirm)
        String toast_confirm;

    //Common
    MaterialBetterSpinner materialBetterSpinner;
    String date;
    Calendar calender = Calendar.getInstance();
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ButterKnife.bind(this);

        // Spinner (Dropdown)
        materialBetterSpinner = (MaterialBetterSpinner)findViewById(R.id.dwn_gender_signup);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SignUpActivity.this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.gender));
        materialBetterSpinner.setAdapter(adapter);
    }

    //DatePicker Dialog to Text
    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datepicker, int year, int month, int dayOfMonth) {
            in_date_signUp.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
        }
    };

    //DatePicker Dialog
    @OnClick(R.id.in_date_signUp)
    public void onClick(View v){
        new DatePickerDialog(SignUpActivity.this, R.style.datepicker, listener,
                calender.get(Calendar.YEAR),calender.get(Calendar.MONTH),calender.get(Calendar.DAY_OF_MONTH)).show();
    }

    //Button Submit SignUp
    @OnClick(R.id.btn_submit_signUp)
    public void btn_submit_signUp_mtd(View v){
        AlertDialog.Builder alertB = new AlertDialog.Builder(SignUpActivity.this, R.style.alertDiag);

        // Title Dialog
        alertB.setTitle(dialog_privacy);

        //Message Dialog
        alertB.setMessage(dialog_coPrivacy);

        // "Accept" Button
        alertB.setPositiveButton(text_accept, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {

                intent = new Intent(SignUpActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), toast_confirm, Toast.LENGTH_LONG).show();

            }
        });

        // "Decline" Button
        alertB.setNegativeButton(text_decline, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertB.show();
    }
}
