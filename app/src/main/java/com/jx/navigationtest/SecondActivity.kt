package com.jx.navigationtest

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener

class SecondActivity : AppCompatActivity(), OnNavigationItemSelectedListener {

    private lateinit var navMenu: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        findViewById<Button>(R.id.back_activity_home).setOnClickListener {
            val mIntent = Intent(this, HomeActivity::class.java)
            startActivity(mIntent)
            finish()
        }

        findViewById<Button>(R.id.start_activity3).setOnClickListener {
            val mIntent = Intent(this, ThirdActivity::class.java)
            startActivity(mIntent)
            finish()
        }

        navMenu = findViewById(R.id.nav_bottom_menu)
        navMenu.selectedItemId = R.id.second
        navMenu.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> startActivity(Intent(this, HomeActivity::class.java))
            R.id.second -> return true
            R.id.third -> startActivity(Intent(this, ThirdActivity::class.java))
        }
        finish()
        return true
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }

}