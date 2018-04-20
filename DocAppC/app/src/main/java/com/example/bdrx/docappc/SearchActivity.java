package com.example.bdrx.docappc;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity {

    //Button
    @BindView(R.id.btn_back_search)
        ImageButton btn_back_search;
    @BindView(R.id.btn_clear_search)
        ImageButton btn_clear_search;

    //EditText
    @BindView(R.id.in_bar_search)
        EditText in_bar_search;

    //Common
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);
        btn_clear_search_mtd(in_bar_search, btn_clear_search);
    }

    @OnClick(R.id.btn_back_search)
    public void btn_back_search_mtd(View v){
        intent = new Intent(SearchActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void btn_clear_search_mtd(final EditText in_bar_search, final ImageButton btn_clear_search) {

        in_bar_search.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && in_bar_search.getText().toString().length() > 0)
                    btn_clear_search.setVisibility(View.VISIBLE);
                else
                    btn_clear_search.setVisibility(View.INVISIBLE);
            }
        });

        btn_clear_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in_bar_search.setText("");
                btn_clear_search.setVisibility(View.INVISIBLE);
            }
        });

        in_bar_search.addTextChangedListener(new TextWatcher() {
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
                    btn_clear_search.setVisibility(View.VISIBLE);
                }else{
                    btn_clear_search.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}
