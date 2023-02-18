package com.example.game

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView

class MainMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_menu)



        val go_back : Button = findViewById(R.id.go_back_button)
        go_back.setOnClickListener{
            startActivity(Intent(this, SplashScreenActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out,)
            finish()
        }

        val new_game : Button = findViewById(R.id.new_game_button)
        new_game.setOnClickListener{
            startActivity(Intent(this, NewGameActivity::class.java))
//            i.putExtra("element_count", 50 )
//            startActivity(i)
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