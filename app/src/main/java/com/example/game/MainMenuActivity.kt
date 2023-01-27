package com.example.game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        val go_back : Button = findViewById(R.id.go_back_button)
        go_back.setOnClickListener{
            val i = Intent(this, SplashScreenActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out,)
            finish()
        }

        val new_game : Button = findViewById(R.id.new_game_button)
        new_game.setOnClickListener{
            startActivity(Intent(this, NewGameActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out,)
        }
    }
}