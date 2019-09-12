package com.aimatus.timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock.elapsedRealtime
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var handler: Handler

    var startTime = elapsedRealtime()
    private lateinit var runnable: Runnable
    private var isTimerRunning = true

    val timer: TextView by lazy {
        findViewById<TextView>(R.id.timer)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initTimer()

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            if (isTimerRunning) {
                handler.removeCallbacks(runnable)
                isTimerRunning = false
            } else {
                handler.postDelayed(runnable, 1)
                isTimerRunning = true
            }
        }
    }

    private fun initTimer() {
        handler = Handler()
        runnable = object : Runnable {
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
