package com.example.bdrx.docappc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotificationActivity extends AppCompatActivity {

    //Button Back
    @BindView(R.id.btn_back_notification)
        ImageButton btn_back_notification;

    //Common
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_back_notification)
    public void btn_back_notification_mtd(View v){
        intent = new Intent(NotificationActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
