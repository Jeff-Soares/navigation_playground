package com.jx.navigationtest

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ViewFlipper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout


class HomeActivity : AppCompatActivity() {

    private val navController: NavController by lazy {
        (supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment)
            .findNavController()
    }
    private lateinit var bottomNavFlipper: ViewFlipper
    private lateinit var bottomNavigation1: BottomNavigationView
    private lateinit var bottomNavigation2: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottomNavFlipper = findViewById(R.id.bottom_nav_flipper)
        bottomNavigation1 = findViewById(R.id.bottom_navigation1)
        bottomNavigation2 = findViewById(R.id.bottom_navigation2)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        }

        findViewById<TabLayout>(R.id.nav_tab_layout).addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> setFirstGraph()
                    1 -> setSecondGraph()
                    else -> return
                }
            }
        })

        setupNavigation()
    }

    private fun setupNavigation() {
        val toolbar = findViewById<Toolbar>(R.id.main_toolbar)
        setSupportActionBar(toolbar)

        val appBarConf = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConf)

        bottomNavigation1.setupWithNavController(navController)
        bottomNavigation1.setOnNavigationItemReselectedListener {}

        bottomNavigation2.setupWithNavController(navController)
        bottomNavigation2.setOnNavigationItemReselectedListener {}
    }

    private fun setFirstGraph() {
        navController.setGraph(R.navigation.first_nav_graph)
        bottomNavFlipper.showPrevious()
    }

    private fun setSecondGraph() {
        navController.setGraph(R.navigation.second_nav_graph)
        bottomNavFlipper.showNext()
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(false)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

}
