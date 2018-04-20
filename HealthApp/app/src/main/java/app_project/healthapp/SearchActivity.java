package app_project.healthapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class SearchActivity extends AppCompatActivity {

    ImageButton imgBtn, imgBtnBack;
    EditText eText;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        imgBtn = (ImageButton)findViewById(R.id.btn_clear);
        eText = (EditText)findViewById(R.id.et_search_bar);
        imgBtnBack = (ImageButton)findViewById(R.id.btn_back_search);
        setRemovableET(eText, imgBtn);

        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void setRemovableET(final EditText eText, final ImageButton imgBtn) {

        eText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && eText.getText().toString().length() > 0)
                    imgBtn.setVisibility(View.VISIBLE);
                else
                    imgBtn.setVisibility(View.INVISIBLE);
            }
        });

        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eText.setText("");
                imgBtn.setVisibility(View.INVISIBLE);
            }
        });

        eText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 0){
                    imgBtn.setVisibility(View.VISIBLE);
                }else{
                    imgBtn.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}
