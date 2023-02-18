package com.example.game

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.Settings

//This service lets us play the background music when we start a new game

class BackgroundService : Service() {
    lateinit var  player : MediaPlayer
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        player = MediaPlayer.create(this, R.raw.audio)
        player.isLooping = true

    }

    override fun onStart(intent: Intent?, startId: Int) {
    }
    override fun onLowMemory() {
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        player.start()
        return START_STICKY
    }

    override fun onDestroy() {
        player.stop()
        player.release()
    }
}