package com.example.jingyahuang_comp304sec002_lab02_ex01

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.CheckBox

class Detached : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var detachedCheckBoxes: Array<CheckBox>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detached)

        sharedPreferences = getSharedPreferences("MySharedPrefs", MODE_PRIVATE)
        detachedCheckBoxes = arrayOf(
            findViewById(R.id.checkBoxDetached1),
            findViewById(R.id.checkBoxDetached2),
            findViewById(R.id.checkBoxDetached3)
        )

        val button = findViewById<Button>(R.id.btnDetached)

        initializeCheckBoxes()

        button.setOnClickListener {
            val type = "Detached"
            val selectedHomes = ArrayList<String>()

            for (checkBox in detachedCheckBoxes) {
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
        when (item.itemId) {
            R.id.menu_apartment -> {
                saveCheckboxStatus()
                directToActivity(Apartment::class.java)
                return true
            }
            R.id.menu_detached -> {
                saveCheckboxStatus()
                return true
            }
            R.id.menu_semi_detached -> {
                saveCheckboxStatus()
                directToActivity(SemiDetached::class.java)
                return true
            }
            R.id.menu_condo -> {
                saveCheckboxStatus()
                directToActivity(Condo::class.java)
                return true
            }
            R.id.menu_townhouse -> {
                saveCheckboxStatus()
                directToActivity(Townhouse::class.java)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onPause() {
        super.onPause()
        // Save checkbox status
        saveCheckboxStatus()
    }

    private fun initializeCheckBoxes() {
        for (checkBox in detachedCheckBoxes) {
            checkBox.isChecked = sharedPreferences.getBoolean("${checkBox.id}Checked", false)
        }
    }

    private fun saveCheckboxStatus() {
        val editor = sharedPreferences.edit()

        for (checkBox in detachedCheckBoxes) {
            editor.putBoolean("${checkBox.id}Checked", checkBox.isChecked)
        }

        editor.apply()
    }

    private fun directToActivity(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        startActivity(intent)
    }
}