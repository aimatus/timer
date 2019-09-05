package com.aimatus.timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock.elapsedRealtime
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    val timer by lazy {
        findViewById<TextView>(R.id.timer)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initTimer()
    }

    private fun initTimer() {
        val startTime = elapsedRealtime()
        val handler = Handler()
        val runnable: Runnable = object : Runnable {
            override fun run() {
                val timeDifference = elapsedRealtime() - startTime
                val seconds = timeDifference / 1000
                val minutes = seconds / 60
                val leftoverSeconds = seconds % 60
                val leftoverMillis = timeDifference % 1000 / 10
                timer.text = String.format("%02d:%02d:%2d", minutes, leftoverSeconds, leftoverMillis)
                handler.postDelayed(this, 10)
            }
        }

        handler.postDelayed(runnable, 1)
    }
}
