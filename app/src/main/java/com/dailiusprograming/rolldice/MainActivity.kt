package com.dailiusprograming.rolldice

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.dailiusprograming.rolldice.databinding.ActivityMainBinding
import com.dailiusprograming.rolldice.databinding.ActivityMainNavBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {

    private val drawerLayout by lazy {
        findViewById<DrawerLayout>(R.id.drawer_layout)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_nav)

//        val button = findViewById<Button>(R.id.btnRoll)
//        button.setOnClickListener {
//            Toast.makeText(this, "Activated", Toast.LENGTH_SHORT).show()
//        }


        val naView = findViewById<NavigationView>(R.id.nav_view)
        naView.setNavigationItemSelectedListener(this)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.open_nav_drawer, R.string.close_nav_drawer
        )

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        drawerLayout.closeDrawer(GravityCompat.START)
//        when(item.itemId){
//            R.id.action_about -> AboutActivity.start(this)
//        }

//        drawerLayout.closeDrawer(GravityCompat.START)
//        when (item.itemId) {
//            R.id.action_about -> AboutActivity.start(this)
//        }
        return true
    }
}