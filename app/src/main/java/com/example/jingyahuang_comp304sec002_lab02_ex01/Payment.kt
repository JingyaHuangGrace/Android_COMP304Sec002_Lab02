package com.example.jingyahuang_comp304sec002_lab02_ex01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast

class Payment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        var radioCash = findViewById<RadioButton>(R.id.radioButtonCash)
        var radioCredit = findViewById<RadioButton>(R.id.radioButtonCredit)
        var radioDebit = findViewById<RadioButton>(R.id.radioButtonDebit)
        var radioGroup = findViewById<RadioGroup>(R.id.radioGroupPayment)
        var button = findViewById<Button>(R.id.btnSubmit)

        button.setOnClickListener{
            var selectedID = radioGroup.checkedRadioButtonId
            if(selectedID != -1) {
                val selectedRadio = findViewById<RadioButton>(selectedID)
                val paymentType = selectedRadio.text.toString()
                val intent = Intent(this, Information::class.java)
                intent.putExtra("paymentType", paymentType)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "Please select a payment type", Toast.LENGTH_SHORT).show()
            }
        }
    }
}