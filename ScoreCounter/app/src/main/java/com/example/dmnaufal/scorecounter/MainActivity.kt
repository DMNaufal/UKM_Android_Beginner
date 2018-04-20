package com.example.dmnaufal.scorecounter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_edit_team_name.*
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.customView
import org.jetbrains.anko.sdk25.coroutines.onClick

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Event Clik btn A
        btn_add_score_a.onClick {
            // increment untuk score
            // TODO 1 : Ambil score terakhir dari text view
            val curScoreA = score_a.text.toString()?.toInt()
            // TODO 2 : incrementing
            val newScore: Int = curScoreA + 1
            // TODO 3 : Set lagi value ke Widget text view
            score_a.text = newScore.toString()
        }
        // Event Clik btn B
        btn_add_score_b.onClick {
            // increment untuk score
            // TODO 1 : Ambil score terakhir dari text view
            val curScoreB = score_b.text.toString()?.toInt()
            // TODO 2 : incrementing
            val newScore: Int = curScoreB + 1
            // TODO 3 : Set lagi value ke Widget text view
            score_b.text = newScore.toString()
        }

        // Event Click untuk ganti nama team
        team_a.onClick {

            // Datapkan nama team yang lama
            var currTeamName = team_a.text.toString()

            var newTeamName: EditText? = null

            // TODO 1 : tampilkan Alert DIalog
            alert {
                title = "Edit Team Name"
             customView {
                     // Buat EditText
                     newTeamName = editText(currTeamName) {
                         hint = "Enter your Team name"
                     }
             }
                // Positive Button
                yesButton {
                    // Set Nilai ke widget text view
                    team_a.text = newTeamName?.text.toString()
                }
            }.show()
        }

        team_b.onClick {

            // Datapkan nama team yang lama
            var currTeamName = team_b.text.toString()

            var newTeamName: EditText? = null

            // TODO 1 : tampilkan Alert DIalog
            alert {
                title = "Edit Team Name"
                customView {
                    // Buat EditText
                    newTeamName = editText(currTeamName) {
                        hint = "Enter your Team name"
                    }
                }
                // Positive Button
                yesButton {
                    // Set Nilai ke widget text view
                    team_b.text = newTeamName?.text.toString()
                }
            }.show()
        }

        // Event OnClick pada button finish
        btn_finish.onClick {
            val team_a_name = team_a.text.toString()
            val team_b_name = team_b.text.toString()

            val team_a_score = score_a.text.toString()
            val team_b_score = score_b.text.toString()

            // start new activity
            startActivity(intentFor<ResultActivity>("NAME_A" to team_a_name, "NAME_B" to team_b_name,
                                                             "SCORE_A" to team_a_score, "SCORE_B" to team_b_score))
        }
    }
}
