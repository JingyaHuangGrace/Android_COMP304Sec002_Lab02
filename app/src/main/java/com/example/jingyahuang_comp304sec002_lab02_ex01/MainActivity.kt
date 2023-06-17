package com.example.jingyahuang_comp304sec002_lab02_ex01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val enter = findViewById<Button>(R.id.btnEnter)
        enter.setOnClickListener{
            val intent = Intent(this,HomeTypes::class.java)
            startActivity(intent)
        }
    }
}