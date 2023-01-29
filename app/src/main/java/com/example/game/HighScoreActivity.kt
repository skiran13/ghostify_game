package com.example.game

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import nl.dionsegijn.konfetti.core.*
import nl.dionsegijn.konfetti.core.emitter.Emitter
import nl.dionsegijn.konfetti.xml.KonfettiView
import java.util.concurrent.TimeUnit
import nl.dionsegijn.konfetti.core.models.Size
import org.w3c.dom.Text

class HighScoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_high_score)

//        Getting Value from memory
        val sharedPref = getSharedPreferences("game.HIGHSCORE",Context.MODE_PRIVATE)
        val highscore = sharedPref.getInt("HIGHSCORE",0)

//        Set highscore on the screen dynamically from the memory
        val highScoreBox : TextView = findViewById(R.id.high_score_text_view)
        highScoreBox.text = highscore.toString()

//        Konfetti code
        var party = Party(
            speed = 10f,
            maxSpeed = 30f,
            damping = 0.9f,
            angle = Angle.RIGHT - 45,
            spread = Spread.SMALL,
            colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
            emitter = Emitter(duration = 5, TimeUnit.SECONDS).perSecond(30),
            position = Position.Relative(0.0, 0.3)
        )

        var viewKonfetti : KonfettiView = findViewById(R.id.konfettiView)
        viewKonfetti.start(listOf(
            party,
            party.copy(
                angle = party.angle - 90, // flip angle from right to left
                position = Position.Relative(1.0, 0.3)
            ),
        ))

//        Go back button functionality
        var goBack : Button = findViewById(R.id.go_back_high_score_to_main_menu)
        goBack.setOnClickListener {
            startActivity(Intent(this, MainMenuActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out,)
            finish()
        }
    }
}