package com.example.jingyahuang_comp304sec002_lab02_ex01

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.CheckBox

class Condo : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var condoCheckBoxes: Array<CheckBox>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_condo)

        sharedPreferences = getSharedPreferences("MySharedPrefs", MODE_PRIVATE)
        condoCheckBoxes = arrayOf(
            findViewById(R.id.checkBoxCondo1),
            findViewById(R.id.checkBoxCondo2),
            findViewById(R.id.checkBoxCondo3)
        )

        val button = findViewById<Button>(R.id.btnCondo)

        initializeCheckBoxes()

        button.setOnClickListener {
            val type = "Condominium"
            val selectedHomes = ArrayList<String>()

            for (checkBox in condoCheckBoxes) {
                if (checkBox.isChecked) {
                    selectedHomes.add(checkBox.text.toString())
                }
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
        return when (item.itemId) {
            R.id.menu_apartment -> {
                saveCheckboxStatus()
                directToApartment()
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

    private fun initializeCheckBoxes() {
        for (checkBox in condoCheckBoxes) {
            checkBox.isChecked = sharedPreferences.getBoolean("${checkBox.id}Checked", false)
        }
    }

    private fun saveCheckboxStatus() {
        val editor = sharedPreferences.edit()

        for (checkBox in condoCheckBoxes) {
            editor.putBoolean("${checkBox.id}Checked", checkBox.isChecked)
        }

        editor.apply()
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