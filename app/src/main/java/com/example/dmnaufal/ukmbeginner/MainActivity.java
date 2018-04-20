package com.example.dmnaufal.ukmbeginner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText edtLength, edtWidth, edtHeight;
    private Button btnCalculate, btnNext;
    private TextView tvResult;
    private static final String STATE_HASIL = "state_hasil";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtLength = (EditText)findViewById(R.id.edt_length);
        edtWidth = (EditText)findViewById(R.id.edt_width);
        edtHeight = (EditText)findViewById(R.id.edt_height);
        btnCalculate = (Button)findViewById(R.id.btn_calculate);
        tvResult = (TextView)findViewById(R.id.tv_result);
        btnNext = (Button)findViewById(R.id.btn_next);
        btnCalculate.setOnClickListener(this);
        btnNext.setOnClickListener(this);

        if (savedInstanceState != null){
            String hasil = savedInstanceState.getString(STATE_HASIL);
            tvResult.setText(hasil);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_calculate:
            String length = edtLength.getText().toString().trim();
            String width = edtWidth.getText().toString().trim();
            String height = edtHeight.getText().toString().trim();
            boolean isEmptyFields = false;
            if (TextUtils.isEmpty(length)){
                isEmptyFields = true;
                edtLength.setError("Field ini tidak boleh kosong");
            }
            if (TextUtils.isEmpty(width)){
                isEmptyFields = true;
                edtWidth.setError("Field ini tidak boleh kosong");
            }
            if (TextUtils.isEmpty(height)){
                isEmptyFields = true;
                edtHeight.setError("Field ini tidak boleh kosong");
            }
            if (!isEmptyFields) {
                double l = Double.parseDouble(length);
                double w = Double.parseDouble(width);
                double h = Double.parseDouble(height);
                double volume = l * w * h;
                tvResult.setText(String.valueOf(volume));
            }
            break;
            case R.id.btn_next:
                Intent nextIntent = new Intent(MainActivity.this, IntentActivity.class);
                startActivity(nextIntent);
            break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        outState.putString(STATE_HASIL, tvResult.getText().toString());
        super.onSaveInstanceState(outState);
    }
}
