package com.example.game

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        val go_back : Button = findViewById(R.id.go_back_button)
        go_back.setOnClickListener{
            startActivity(Intent(this, SplashScreenActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out,)
            finish()
        }

        val new_game : Button = findViewById(R.id.new_game_button)
        new_game.setOnClickListener{
            val i = Intent(this, ValidationActivity::class.java)
            i.putExtra("element_count", 50 )
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out,)
        }

        val high_score : Button = findViewById(R.id.high_score_button)
        high_score.setOnClickListener {
            startActivity(Intent(this, HighScoreActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out,)
            finish()

            }
    }
}