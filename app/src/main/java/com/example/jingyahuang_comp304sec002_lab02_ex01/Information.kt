package com.example.jingyahuang_comp304sec002_lab02_ex01

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class Information : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)

        val paymentType = intent.getStringExtra("paymentType")
        val name = findViewById<EditText>(R.id.editTextName)
        val address = findViewById<EditText>(R.id.editTextAddress)
        val phone = findViewById<EditText>(R.id.editTextPhone)
        val email = findViewById<EditText>(R.id.editTextEmailAddress)
        val game = findViewById<EditText>(R.id.editTextGame)
        val book = findViewById<EditText>(R.id.editTextBook)
        val payment = findViewById<EditText>(R.id.editTextPayment)
        val validation = findViewById<TextView>(R.id.textViewValidation)

        payment.hint = when(paymentType)
        {
            "Cash" -> "Cash Amount"
            "Credit Card" -> "Credit Card No."
            "Debit Card" -> "Debit Card No."
            else ->"Payment Details"
        }

        val button = findViewById<Button>(R.id.btnSubmitInfo)
        button.setOnClickListener{
            if(name.text.isEmpty())
                validation.text = "Please input your name."
            else if (address.text.isEmpty())
                validation.text = "Please input your address."
            else if (phone.text.isEmpty()||phone.text.length != 10)
                validation.text = "Please input a 10-digit phone number."
            else if (email.text.isEmpty()||!email.text.contains('@'))
                validation.text = "Please input a valid email address."
            else if (game.text.isEmpty())
                validation.text = "Please input your favorite game title."
            else if (book.text.isEmpty())
                validation.text = "Please input your favorite book title."
            else if (paymentType!= "Cash"&& (payment.text.isEmpty()||payment.text.toString().length !=16))
                validation.text = "Please input a valid bank card number."
            else
            {
                name.text.clear()
                address.text.clear()
                phone.text.clear()
                email.text.clear()
                game.text.clear()
                book.text.clear()
                payment.text.clear()
                validation.text = "Thank you. Your submission has been received!"

                sharedPreferences = getSharedPreferences("MySharedPrefs", MODE_PRIVATE)
                sharedPreferences.edit().clear().apply()

            }

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_hometype, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_apartment -> {
                directToApartment()
                true
            }
            R.id.menu_detached -> {
                directToDetached()
                true
            }
            R.id.menu_semi_detached -> {
                directToSemiDetached()
                true
            }
            R.id.menu_condo -> {

                true
            }
            R.id.menu_townhouse -> {
                directToTownhouse()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun directToApartment() {
        val intent = Intent(this, Apartment::class.java)
        startActivity(intent)
    }

    private fun directToDetached() {
        val intent = Intent(this, Detached::class.java)
        startActivity(intent)
    }

    private fun directToSemiDetached() {
        val intent = Intent(this, SemiDetached::class.java)
        startActivity(intent)
    }

    private fun directToTownhouse() {
        val intent = Intent(this, Townhouse::class.java)
        startActivity(intent)
    }
}