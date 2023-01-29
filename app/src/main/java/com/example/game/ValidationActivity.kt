package com.example.game

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView


class ValidationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_validation)

        var submitValidation : Button = findViewById(R.id.submit_validation)
        submitValidation.setOnClickListener{
            var validationLayout : LinearLayout = findViewById(R.id.validation_layout)

            validationLayout.animate()
                            .alpha(0f)
                            .setDuration(500)
                            .setListener(object : AnimatorListenerAdapter() {
                                override fun onAnimationEnd(animation: Animator) {
                                    validationLayout.visibility = View.INVISIBLE
                                }
                            })

            var spottedElements : EditText = findViewById(R.id.spotted_elements)
            var elementCount = intent.extras?.get("element_count")

            val i = Intent(this, ResultActivity::class.java)
            if(spottedElements.text.toString() ==  elementCount.toString()) {
                i.putExtra("result", "SUCCESS" )
            } else {
                i.putExtra("result", "FAIL" )
            }
            i.putExtra("spotted_elements", spottedElements.text.toString())
            i.putExtra("element_count",elementCount.toString())
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out,)
            finish()
        }
    }
}

