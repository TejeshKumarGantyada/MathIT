package com.tejesh.mathit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.Locale
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    lateinit var score: TextView
    lateinit var scoreNum: TextView
    lateinit var life: TextView
    lateinit var lifeNum: TextView
    lateinit var time: TextView
    lateinit var timeNum: TextView

    lateinit var question: TextView
    lateinit var answer: EditText

    lateinit var ok: Button
    lateinit var next: Button

    var correctAnswer = 0
    var gameScore = 0
    var gameLife = 3

    var answerChecked = false
    var receivedValue: Int = 0


    var timer: CountDownTimer? = null
    private val startTimerInMillis: Long = 20000
    var timeLeftInMillis: Long = startTimerInMillis


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        receivedValue = intent.getIntExtra("key", 0)


        supportActionBar!!.title = "Addition"
        score = findViewById(R.id.score)
        scoreNum = findViewById(R.id.scoreNum)
        life = findViewById(R.id.life)
        lifeNum = findViewById(R.id.lifeNum)
        time = findViewById(R.id.time)
        timeNum = findViewById(R.id.timeNum)
        question = findViewById(R.id.question)
        answer = findViewById(R.id.answer)

        ok = findViewById(R.id.ok)
        next = findViewById(R.id.next)

        gameContinue()

        ok.setOnClickListener {
            pauseTimer()
            if (!answerChecked) {

                val input = answer.text.toString()

                if (input == "") {
                    Toast.makeText(applicationContext, "Please write an answer or click next.", Toast.LENGTH_LONG).show()
                } else {

                    val userAnswer = input.toInt()

                    if (userAnswer == correctAnswer) {
                        gameScore = gameScore + 10
                        question.text = "Correct Answer!"
                        scoreNum.text = gameScore.toString()
                    } else {
                        gameLife--
                        question.text = "Wrong Answer!"
                        lifeNum.text = gameLife.toString()
                        if (gameLife == 0) {
                            Toast.makeText(applicationContext, "Game Over", Toast.LENGTH_LONG).show()
                            val intent = Intent(this@GameActivity, EndActivity::class.java)
                            intent.putExtra("score", gameScore)
                            startActivity(intent)
                            finish()
                        }
                    }

                    answerChecked = true
                }
            }
        }

        next.setOnClickListener {
            pauseTimer()
            resetTimer()
            answer.setText("")
            answerChecked = false

            if(gameLife == 0){
                Toast.makeText(applicationContext, "Game Over", Toast.LENGTH_LONG).show()
                val intent = Intent(this@GameActivity, EndActivity::class.java)
                intent.putExtra("score", gameScore)
                startActivity(intent)
                finish()
            }
            else{
                gameContinue()
            }

        }

    }

    fun gameContinue(){

        val num1 = Random.nextInt(0,100)
        val num2 = Random.nextInt(0,100)

        if(receivedValue == 1){
            question.text = "$num1 + $num2"
            correctAnswer = num1 + num2
        }
        else if(receivedValue == 2){
            question.text = "$num1 - $num2"
            correctAnswer = num1 - num2
        }
        else if(receivedValue == 3){
            question.text = "$num1 * $num2"
            correctAnswer = num1 * num2
        }
        else if(receivedValue == 4){
            question.text = "$num1 / $num2"
            correctAnswer = num1 / num2
        }
        startTimer()

    }

    fun startTimer(){
        timer?.cancel()
        timer = object: CountDownTimer(timeLeftInMillis, 1000){
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateText()
            }

            override fun onFinish() {
                pauseTimer()
                resetTimer()
                updateText()

                gameLife--
                lifeNum.text = gameLife.toString()
                question.text = "Sorry, Time's up!"
            }

        }.start()
    }

    fun updateText(){
        val remainingTime: Int = (timeLeftInMillis/1000).toInt()
        timeNum.text = String.format(Locale.getDefault(), "%02d", remainingTime)
    }

    fun resetTimer(){
        timer?.cancel()
        timer = null
    }

    fun pauseTimer(){
        timeLeftInMillis = startTimerInMillis
        updateText()
        timer?.cancel()
    }
}