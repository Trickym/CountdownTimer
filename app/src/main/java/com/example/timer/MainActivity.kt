package com.example.timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.inputmethod.InputBinding
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import java.util.*

class MainActivity : AppCompatActivity() {
    private var START_TIME_IN_MILLIS:Long = 600000
    private lateinit var timeView : TextView
    private lateinit var startButton:MaterialButton
    private lateinit var resetButton:MaterialButton

    private lateinit var countDown:CountDownTimer
    private var timerRunning:Boolean = false
    private var timeLeft:Long = START_TIME_IN_MILLIS
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timeView = findViewById(R.id.time_view)
        startButton = findViewById(R.id.start_btn)
        resetButton = findViewById(R.id.reset_btn)

        startButton.setOnClickListener {
            if(timerRunning){
                pauseTimer()
            }
            else{
                startTimer()
            }
        }

        resetButton.setOnClickListener {
            resetTimer()
        }

        updateCountdown()

    }

    private fun resetTimer() {
        timeLeft = START_TIME_IN_MILLIS
        updateCountdown()
    }

    private fun pauseTimer() {
        countDown.cancel()
        timerRunning=false
        startButton.text="START"
        startButton.icon = getDrawable(R.drawable.ic_baseline_play_arrow_24)
    }

    private fun startTimer(){
       countDown = object:CountDownTimer(timeLeft,1000){
           override fun onTick(p0: Long) {
               timeLeft = p0
               updateCountdown();
           }

           override fun onFinish() {
               timerRunning=false
               startButton.text="START"
               startButton.icon = getDrawable(R.drawable.ic_baseline_play_arrow_24)
           }

       }.start()
        timerRunning = true
        startButton.text = "Pause"
        startButton.icon = getDrawable(R.drawable.ic_baseline_pause_24)
    }

    private fun updateCountdown() {
        var minutes : Int = (timeLeft.toInt()/1000)/60
        var seconds : Int = (timeLeft.toInt()/1000)%60
        var timeFormatted:String = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds)
        timeView.text = timeFormatted
    }
}