package com.example.game

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class NewGameActivity : AppCompatActivity() {

    lateinit var audio : Intent
    lateinit var countDownTimer : CountDownTimer
    override fun onStop() {
        super.onStop()
        countDownTimer.cancel()
        stopService(audio)
    }
//    var lateinit:Int imageWidth
//    var lateinit:Int imageHeight
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    requestWindowFeature(Window.FEATURE_NO_TITLE)
    getWindow().setFlags(
        WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_new_game)


    }
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

        var instruction_text : TextView = findViewById(R.id.instruction_text)
//        instruction_text.text = intent.extras!!.getString("key")

        var gameStarted = false
        val initialCountDown: Long = 20000
        val countDownInterval: Long = 1000
        var timeLeft_text : TextView = findViewById(R.id.timer_text)
        var count = 0
        val parentLayout = findViewById<RelativeLayout>(R.id.game_screen_layout)
        val imageView = ImageView(this)
        imageView.setImageResource(R.mipmap.ic_channel_foreground)
        // Randomly select an image
//        val randomIndex = Random.nextInt(imageIds.size)
//        val imageId = imageIds[randomIndex]

        // Set the image to the ImageView


        val params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)

        fun end_game() {
            Toast.makeText(this, "Game Ended", Toast.LENGTH_SHORT).show()
            gameStarted = false
            finish()
            val i = Intent(this, ValidationActivity::class.java)
            i.putExtra("element_count", count )
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
        fun start_game() {

            val initialTimeLeft = initialCountDown / 1000
            timeLeft_text.text = "Time Left: ${initialTimeLeft.toString()}"

            countDownTimer = object: CountDownTimer(initialCountDown, countDownInterval) {
                override fun onTick(p0: Long) {
                    val timeLeft = p0 / 1000
                    timeLeft_text.text = "Time Left: ${timeLeft.toString()}"
                }

                override fun onFinish() {
//                    timeLeft_text.text = "Game Over !"
                    end_game()
                    println("No. of ghosts:$count")
                    stopService(audio)
                }
            }

            countDownTimer.start()
            gameStarted = true
        }

//        imageView.setImageResource(R.mipmap.ic_channel_foreground)
//        var countGhosts = (5..15).shuffled().last()
//        val milliSeconds: Long = initialCountDown/countGhosts
//        val seconds: Int = ((initialCountDown/1000).toInt()/countGhosts)
        val go_btn : Button = findViewById(R.id.go_button)
        val rule1 : View = findViewById(R.id.hotizontal_rule1)
        val rule2 : View = findViewById(R.id.hotizontal_rule2)
        fun fadeOutAndHideImage(img: ImageView, delay: Long) {
            val fadeOut = AlphaAnimation(1F, 0F)
            fadeOut.setInterpolator(AccelerateInterpolator())
            fadeOut.setDuration(delay)

            fadeOut.setAnimationListener(object: Animation.AnimationListener {
                override fun onAnimationEnd(animation:Animation) {
                    img.setVisibility(ImageView.GONE)
                }
                override fun onAnimationRepeat(animation:Animation) {}
                override  fun onAnimationStart(animation:Animation) {}
            })
            img.startAnimation(fadeOut)
        }


        fun toggleImage() {
            if (gameStarted == false) return

//                var leftMargin = Random.nextInt(parentLayout.width - 200)//imageView.width)
//                var topMargin = Random.nextInt(parentLayout.height - 200)//imageView.height)
//                var rightMargin = Random.nextInt(parentLayout.width - 200)//imageView.width)
//                var bottomMargin = Random.nextInt(parentLayout.height - 200)

            params.setMargins(0, 0, 0, 0)
            imageView.layoutParams = params
            imageView.x = Random.nextInt(parentLayout.width - 500).toFloat()
            imageView.y = Random.nextInt(parentLayout.height - 550).toFloat()
            if (count==0) {
                parentLayout.addView(imageView)

//                fadeInImage(imageView, parentLayout)
            }
            if (imageView.visibility == ImageView.GONE) {
                imageView.visibility = ImageView.VISIBLE
            }
            count++
            val delay: Long = Random.nextLong(1000, 2500)
            Handler().postDelayed({
//                imageView.visibility = ImageView.INVISIBLE
                fadeOutAndHideImage(imageView, delay)
                Handler().postDelayed({ toggleImage() }, delay)//Random.nextLong(1000, 2000))
            }, 1000)
//                parentLayout.removeView(imageView)

        }
        go_btn.setOnClickListener{
            go_btn.visibility = View.INVISIBLE
//            instruction_text.text = "width :${Random.nextInt(parentLayout.width - 250) + 50} height:${Random.nextInt(parentLayout.height - 500) + 100}"
            instruction_text.visibility = View.GONE
            timeLeft_text.visibility = View.VISIBLE
            rule1.visibility = View.VISIBLE
            rule2.visibility = View.VISIBLE

            println("Image width: ${imageView.measuredWidth}")
            println("Image height: ${imageView.measuredHeight}")
            start_game()
            toggleImage()
            audio = Intent(this, BackgroundService::class.java)
            startService(audio)

        }
//        imageView.setImageResource(R.mipmap.ic_channel_foreground)
//        imageWidth = imageView.width
//        imageHeight = imageView.height
    }

}