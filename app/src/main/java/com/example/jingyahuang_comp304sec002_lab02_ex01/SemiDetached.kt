package com.example.jingyahuang_comp304sec002_lab02_ex01

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.CheckBox

class SemiDetached : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var semiCheckBoxes: Array<CheckBox>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_semi_detached)

        sharedPreferences = getSharedPreferences("MySharedPrefs", MODE_PRIVATE)
        semiCheckBoxes = arrayOf(
            findViewById(R.id.checkBoxSemi1),
            findViewById(R.id.checkBoxSemi2),
            findViewById(R.id.checkBoxSemi3)
        )

        val button = findViewById<Button>(R.id.btnSemi)

        initializeCheckBoxes()

        button.setOnClickListener {
            val type = "Semi-Detached"
            val selectedHomes = ArrayList<String>()

            for (checkBox in semiCheckBoxes) {
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
            R.id.menu_apartment,
            R.id.menu_detached,
            R.id.menu_semi_detached,
            R.id.menu_condo,
            R.id.menu_townhouse -> {
                saveCheckboxStatus()
                directToActivity(item.itemId)
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
        for (checkBox in semiCheckBoxes) {
            checkBox.isChecked = sharedPreferences.getBoolean("${checkBox.id}Checked", false)
        }
    }

    private fun saveCheckboxStatus() {
        val editor = sharedPreferences.edit()

        for (checkBox in semiCheckBoxes) {
            editor.putBoolean("${checkBox.id}Checked", checkBox.isChecked)
        }

        editor.apply()
    }

    private fun directToActivity(itemId: Int) {
        val intent = when (itemId) {
            R.id.menu_apartment -> Intent(this, Apartment::class.java)
            R.id.menu_detached -> Intent(this, Detached::class.java)
            R.id.menu_semi_detached -> Intent(this, SemiDetached::class.java)
            R.id.menu_condo -> Intent(this, Condo::class.java)
            R.id.menu_townhouse -> Intent(this, Townhouse::class.java)
            else -> null
        }

        intent?.let {
            startActivity(intent)
        }
    }
}