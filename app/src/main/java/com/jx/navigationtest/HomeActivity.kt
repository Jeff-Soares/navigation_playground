package com.jx.navigationtest

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener

class HomeActivity : AppCompatActivity(), OnNavigationItemSelectedListener {

    private lateinit var navMenu: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        findViewById<Button>(R.id.start_activity2).setOnClickListener {
            val mIntent = Intent(this, SecondActivity::class.java)
            startActivity(mIntent)
            finish()
        }

        navMenu = findViewById(R.id.nav_bottom_menu)
        navMenu.selectedItemId = R.id.home
        navMenu.setOnNavigationItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> return true
            R.id.second -> startActivity(Intent(this, SecondActivity::class.java))
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