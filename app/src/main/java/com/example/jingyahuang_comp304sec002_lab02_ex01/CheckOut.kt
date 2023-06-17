package com.example.jingyahuang_comp304sec002_lab02_ex01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast

class CheckOut : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_out)

        val selectedHomes = intent.getStringArrayListExtra("selectedHomes")?: emptyList<String>()

        val type = findViewById<TextView>(R.id.textViewType)
        val radioHome1 = findViewById<RadioButton>(R.id.radioButtonHome1)
        val radioHome2 = findViewById<RadioButton>(R.id.radioButtonHome2)
        val radioHome3 = findViewById<RadioButton>(R.id.radioButtonHome3)

        type.text = intent.getStringExtra("type")
        radioHome1.text = selectedHomes.getOrNull(0) ?: ""
        radioHome2.text = selectedHomes.getOrNull(1) ?: ""
        radioHome3.text = selectedHomes.getOrNull(2) ?: ""

        val button = findViewById<Button>(R.id.btnNext)
        button.setOnClickListener{
            if (radioHome1.isChecked || radioHome2.isChecked || radioHome3.isChecked)
            {
                val intent = Intent(this, Payment::class.java)
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "Please select a home", Toast.LENGTH_SHORT).show()
            }
        }
    }
}