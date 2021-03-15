package com.dailiusprograming.rolldice

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.*

import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var myToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_nav)

        navController = findNavController(R.id.navHostFragment)
        drawerLayout = findViewById(R.id.drawer_layout)
        myToolbar = findViewById(R.id.toolbar)
        setSupportActionBar(myToolbar)

        val toggle = object : ActionBarDrawerToggle(
            this, drawerLayout, myToolbar,
            R.string.open_nav_drawer, R.string.close_nav_drawer
        ){}

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val navView = findViewById<NavigationView>(R.id.nav_view)
        navView.setNavigationItemSelectedListener(this)

        setupNavigationDrawer()
    }

    private fun setupNavigationDrawer() {
        val appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        NavigationUI.setupWithNavController(myToolbar, navController, appBarConfiguration)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawerLayout.closeDrawer(GravityCompat.START)
        when(item.itemId){
            R.id.settingsFragment -> {
                navController.navigate(R.id.action_diceFragment_to_settingsFragment)
            }
            R.id.action_about -> {
                navController.navigate(R.id.action_settingsFragment_to_diceFragment)
            }
        }
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
                || super.onSupportNavigateUp()
    }
}