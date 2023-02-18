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

//This is the Main menu screen from where we can start a new game, check our highscore and go back to splash screen
class MainMenuActivity : AppCompatActivity() {
    override fun onDestroy() {
        super.onDestroy()
        var audio = Intent(this, BackgroundService::class.java)
        stopService(audio)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_menu)


//  This button is used to go back to the splash screen
        val go_back : Button = findViewById(R.id.go_back_button)
        go_back.setOnClickListener{
            startActivity(Intent(this, SplashScreenActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out,)
            finish()
        }

//  This button is used to start a new game (NewGameActivity)
        val new_game : Button = findViewById(R.id.new_game_button)
        new_game.setOnClickListener{
            startActivity(Intent(this, NewGameActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out,)
        }

//  This button is used to check the highscore of the user (HighScoreActivity)
        val high_score : Button = findViewById(R.id.high_score_button)
        high_score.setOnClickListener {
            startActivity(Intent(this, HighScoreActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out,)
            finish()

            }
    }
}