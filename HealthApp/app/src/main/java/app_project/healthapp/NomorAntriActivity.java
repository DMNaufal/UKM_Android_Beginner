package app_project.healthapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class NomorAntriActivity extends AppCompatActivity {

    ImageButton btnBack, btnCancel, btnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nomor_antri);

        btnBack = (ImageButton)findViewById(R.id.btn_back_nomor_antri);
        btnCancel = (ImageButton)findViewById(R.id.btn_cancel_nomor_antri);
        btnCall = (ImageButton)findViewById(R.id.btn_call_nomor_antri);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NomorAntriActivity.this, NotificationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertB = new AlertDialog.Builder(NomorAntriActivity.this, R.style.alertDiag);

                // Title Dialog
                alertB.setTitle("Apa Anda Yakin?");

                //Message Dialog
                alertB.setMessage("Klik tombol Accept untuk melanjutkan");

                // "Accept" Button
                alertB.setPositiveButton("ACCEPT", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {

                        Intent intent = new Intent(NomorAntriActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Pesanan berhasil dibatalkan",
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
        // End

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Calling...",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
