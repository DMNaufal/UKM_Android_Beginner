package com.example.dmnaufal.findmyage

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import java.time.Year
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //beri event OnClick terhadap button
        btnFinfAge.setOnClickListener {

            if (etBirthYear.text.isNotEmpty()) {
                //dapatkan nilai dari Edit Text => convert ke Integer
                val year: Int = etBirthYear.text.toString()?.toInt()
                val currentYear: Int = Calendar.getInstance().get(Calendar.YEAR)

                //Calculate
                val resultYear = currentYear - year

                //Set Value into widget

                tvResultAge.text = "Your age is : ${resultYear.toString()} years old"
            } else{
                etBirthYear.error = "Please enter valid year!"
                etBirthYear.requestFocus()
            }
        }

        btnNext.setOnClickListener {

            var intent = Intent(this,Main2Activity::class.java)
            startActivity(intent)
        }
    }
}
