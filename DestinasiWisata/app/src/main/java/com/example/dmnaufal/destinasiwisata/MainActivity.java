package com.example.dmnaufal.destinasiwisata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dmnaufal.destinasiwisata.db.facade.Facade;
import com.example.dmnaufal.destinasiwisata.db.facade.ManageHomeTbl;
import com.example.dmnaufal.destinasiwisata.model.ResponseWisata;
import com.infideap.atomic.Atom;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Atom.with(this)
                .load("url webservice")
                .setJsonPojoBody(ResponseWisata.class)
                .se
    }
}
