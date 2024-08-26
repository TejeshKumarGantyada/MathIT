package com.tejesh.mathit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var addition: Button
    lateinit var substraction: Button
    lateinit var multiplication: Button
    lateinit var  division: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addition = findViewById(R.id.add)
        substraction = findViewById(R.id.sub)
        multiplication = findViewById(R.id.mul)
        division = findViewById(R.id.div)

        addition.setOnClickListener {

            val intent = Intent(this@MainActivity, GameActivity::class.java)
            intent.putExtra("key", 1)
            startActivity(intent)

        }

        substraction.setOnClickListener {

            val intent = Intent(this@MainActivity, GameActivity::class.java)
            intent.putExtra("key", 2)
            startActivity(intent)

        }

        multiplication.setOnClickListener {

            val intent = Intent(this@MainActivity, GameActivity::class.java)
            intent.putExtra("key", 3)
            startActivity(intent)

        }

        division.setOnClickListener {

            val intent = Intent(this@MainActivity, GameActivity::class.java)
            intent.putExtra("key", 4)
            startActivity(intent)

        }

    }
}