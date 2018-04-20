package com.example.dmnaufal.findmyage

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    val data_item = arrayOf("Kotlin", "Java", "PHP", "C##", "C++", "Phyton", "Groovy")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val adapterList = ArrayAdapter<String>(applicationContext, android.R.layout.simple_list_item_1, data_item)

        //Set adapter ke widget
        lvListItem.adapter = adapterList
        // event On item click
        lvListItem.setOnItemClickListener { adapterView, view, i, l ->
            //tampilkan toast
            Toast.makeText(this@Main2Activity, "${data_item.get(i)} Selected!", Toast.LENGTH_SHORT).show()
        }
    }
}
