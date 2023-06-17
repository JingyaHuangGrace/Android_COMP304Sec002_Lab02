package com.example.jingyahuang_comp304sec002_lab02_ex01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class Townhouse : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_townhouse)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_hometype, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId)
        {
            R.id.menu_apartment ->
            {
                directToApartment()
                true
            }

            R.id.menu_detached ->
            {
                directToDetached()
                true
            }

            R.id.menu_semi_detached ->
            {
                directToSemiDetached()
                true
            }

            R.id.menu_condo ->
            {
                directToCondo()
                true
            }

            R.id.menu_townhouse ->
            {
                directToTownhouse()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun directToApartment()
    {
        val intent = Intent(this, Apartment::class.java)
        startActivity(intent)
    }

    private fun directToDetached()
    {
        val intent = Intent(this, Detached::class.java)
        startActivity(intent)
    }

    private fun directToSemiDetached()
    {
        val intent = Intent(this, SemiDetached::class.java)
        startActivity(intent)
    }

    private fun directToCondo()
    {
        val intent = Intent(this, Condo::class.java)
        startActivity(intent)
    }

    private fun directToTownhouse()
    {
        val intent = Intent(this, Townhouse::class.java)
        startActivity(intent)
    }
}