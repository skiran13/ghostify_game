package com.example.game

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import nl.dionsegijn.konfetti.core.Angle
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.Rotation
import nl.dionsegijn.konfetti.core.emitter.Emitter
import nl.dionsegijn.konfetti.core.models.Size
import nl.dionsegijn.konfetti.xml.KonfettiView
import java.util.concurrent.TimeUnit

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        var result = intent.extras?.get("result")
        var resultView = findViewById<TextView>(R.id.result)
        if (result == "SUCCESS") {
            resultView.text = "Congratulations!!"
            resultView.setTextColor(ContextCompat.getColor(this, R.color.success))
            resultView.background = ContextCompat.getDrawable(this, R.drawable.success)

            val party = Party(
                speed = 30f,
                maxSpeed = 50f,
                damping = 0.9f,
                angle = Angle.TOP,
                spread = 45,
                size = listOf(Size.SMALL, Size.LARGE),
                timeToLive = 3000L,
                rotation = Rotation(),
                colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
                emitter = Emitter(duration = 100, TimeUnit.MILLISECONDS).max(30),
                position = Position.Relative(0.5, 0.5)
            )

            findViewById<KonfettiView>(R.id.konfettiView).start(listOf(
                Party(
                    speed = 0f,
                    maxSpeed = 30f,
                    damping = 0.9f,
                    spread = 360,
                    colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
                    emitter = Emitter(duration = 100, TimeUnit.MILLISECONDS).max(100),
                    position = Position.Relative(0.5, 0.2)
                )
            ))


        } else if (result == "FAIL") {
            resultView.text = "Oh no!!\nWrong Answer"
            resultView.setTextColor(ContextCompat.getColor(this, R.color.fail))
            resultView.background = ContextCompat.getDrawable(this, R.drawable.fail)

        } else {

            resultView.text = "Error!!"
            resultView.background = R.drawable.fail.toDrawable()
        }

        var spottedElements = intent.extras?.get("spotted_elements")
        var elementCount = intent.extras?.get("element_count")
        spottedElements = spottedElements.toString().toFloat()
        elementCount = elementCount.toString().toFloat()
        var score : Float = 0f
        if(spottedElements < elementCount) {
            score = (spottedElements/elementCount) * 100
        } else {
            score = (((2 * elementCount) -  spottedElements) / elementCount) * 100
        }

        findViewById<TextView>(R.id.score).text = score.toInt().toString()

        val sharedPref = getSharedPreferences("game.HIGHSCORE", Context.MODE_PRIVATE)
        val highscore = sharedPref.getInt("HIGHSCORE",0)
        if (score.toInt() > highscore) {
            with(sharedPref.edit()) {
                putInt("HIGHSCORE",score.toInt())
                apply()
            }
        }

        findViewById<Button>(R.id.ok_button).setOnClickListener{
            startActivity(Intent(this, MainMenuActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out,)
            finish()
        }
    }
}