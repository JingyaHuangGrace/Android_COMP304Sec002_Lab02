package com.example.jingyahuang_comp304sec002_lab02_ex01

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.CheckBox

class Apartment : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apartment)

        sharedPreferences = getSharedPreferences("MySharedPrefs", MODE_PRIVATE)

        var apartment1 = findViewById<CheckBox>(R.id.checkBoxApartment1)
        var apartment2 = findViewById<CheckBox>(R.id.checkBoxApartment2)
        var apartment3 = findViewById<CheckBox>(R.id.checkBoxApartment3)

        var button = findViewById<Button>(R.id.btnApartment)

        apartment1.isChecked = sharedPreferences.getBoolean("apartment1Checked", false)
        apartment2.isChecked = sharedPreferences.getBoolean("apartment2Checked", false)
        apartment3.isChecked = sharedPreferences.getBoolean("apartment3Checked", false)

        button.setOnClickListener {
            val type = "Apartment"
            val selectedHomes = ArrayList<String>()

            if (apartment1.isChecked) {
                selectedHomes.add(apartment1.text.toString())
            }
            if (apartment2.isChecked) {
                selectedHomes.add(apartment2.text.toString())
            }
            if (apartment3.isChecked) {
                selectedHomes.add(apartment3.text.toString())
            }

            val intent = Intent(this, CheckOut::class.java)
            intent.putExtra("type", type)
            intent.putStringArrayListExtra("selectedHomes", selectedHomes)
            startActivity(intent)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_hometype, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menu_apartment -> {
                saveCheckboxStatus()
                true
            }
            R.id.menu_detached -> {
                saveCheckboxStatus()

                directToDetached()
                true
            }
            R.id.menu_semi_detached -> {
                saveCheckboxStatus()

                directToSemiDetached()
                true
            }
            R.id.menu_condo -> {
                saveCheckboxStatus()

                directToCondo()
                true
            }
            R.id.menu_townhouse -> {
                saveCheckboxStatus()

                directToTownhouse()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onPause() {
        super.onPause()
        // Save checkbox status
        saveCheckboxStatus()
    }

    private fun saveCheckboxStatus() {
        val editor = sharedPreferences.edit()
        val apartment1 = findViewById<CheckBox>(R.id.checkBoxApartment1)
        val apartment2 = findViewById<CheckBox>(R.id.checkBoxApartment2)
        val apartment3 = findViewById<CheckBox>(R.id.checkBoxApartment3)

        editor.putBoolean("apartment1Checked", apartment1.isChecked)
        editor.putBoolean("apartment2Checked", apartment2.isChecked)
        editor.putBoolean("apartment3Checked", apartment3.isChecked)
        editor.apply()
    }

    private fun directToDetached() {
        val intent = Intent(this, Detached::class.java)
        startActivity(intent)
    }

    private fun directToSemiDetached() {
        val intent = Intent(this, SemiDetached::class.java)
        startActivity(intent)
    }

    private fun directToCondo() {
        val intent = Intent(this, Condo::class.java)
        startActivity(intent)
    }

    private fun directToTownhouse() {
        val intent = Intent(this, Townhouse::class.java)
        startActivity(intent)
    }
}