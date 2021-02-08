package com.dailiusprograming.rolldice

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.dailiusprograming.rolldice.databinding.ActivityMainBinding
import com.dailiusprograming.rolldice.databinding.ActivityMainNavBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {

    private lateinit var bindingActivityMainNav: ActivityMainNavBinding
//    private lateinit var bindingActivityMain: ActivityMainBinding
    private lateinit var drawerLayouttt: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_nav)

        bindingActivityMainNav = ActivityMainNavBinding.inflate(layoutInflater)
        setContentView(bindingActivityMainNav.root)

//        bindingActivityMain = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(bindingActivityMain.root)

        drawerLayouttt = bindingActivityMainNav.drawerLayout

//        val toolbar = bindingActivityMain.toolbar
//        setSupportActionBar(toolbar)

        val naView = bindingActivityMainNav.navView
        naView.setNavigationItemSelectedListener(this)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayouttt, toolbar,
            R.string.open_nav_drawer, R.string.close_nav_drawer
        )

        drawerLayouttt.addDrawerListener(toggle)
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