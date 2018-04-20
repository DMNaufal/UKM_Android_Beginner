package com.example.dmnaufal.scorecounter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    var teamAName: String ? = null
    var teamBName: String ? = null
    var teamAScore: String ? = null
    var teamBScore: String ? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // Inisialisasi
        teamAName = intent.getStringExtra("NAME_A")
        teamBName = intent.getStringExtra("NAME_B")
        teamAScore = intent.getStringExtra("SCORE_A")
        teamBScore = intent.getStringExtra("SCORE_B")

        // cari pemenang
        if (teamAScore?.toInt()!! > teamBScore?.toInt()!!){
            winner_team.text = teamAName
        } else if (teamAScore?.toInt()!! < teamBScore?.toInt()!!){
            winner_team.text = teamBName
        } else {
            winner_team.text = "DRAW"
        }

        // set score
        team_a.text = "$teamAName : $teamAScore"
        team_b.text = "$teamBName : $teamBScore"
    }
}
