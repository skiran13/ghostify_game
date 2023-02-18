package com.example.game

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
//This activity validates the user's guess

class ValidationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_validation)

        //Once the user makes a guess, their answer is validated with the original answer and the
        // result is passed to the next activity
        var submitValidation : Button = findViewById(R.id.submit_validation)
        submitValidation.setOnClickListener{

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

