package com.example.game

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val splashimg : ImageView = findViewById(R.id.iv_uottawa)
        val slideAnimation = AnimationUtils.loadAnimation(this,R.anim.slide)
        val button : Button = findViewById(R.id.button1)
        button.setOnClickListener{
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out,)
            finish()
        }
//        splashimg.startAnimation(slideAnimation)
//        splashimg.animate().setDuration(3000).alpha(1f).withEndAction {
//            val i = Intent(this, MainActivity::class.java)
//            startActivity(i)
//            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out,)
//            finish()
//        }
    }
}