package com.dailiusprograming.rolldice

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.DialogFragment
import com.dailiusprograming.rolldice.dialogs.SettingsDialog
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {

    private val drawerLayout by lazy {
        findViewById<DrawerLayout>(R.id.drawer_layout)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_nav)

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
        drawerLayout.closeDrawer(GravityCompat.START)
        when(item.itemId){
            R.id.action_settings -> openSettingsDialog()
        }

//        drawerLayout.closeDrawer(GravityCompat.START)
//        when (item.itemId) {
//            R.id.action_about -> AboutActivity.start(this)
//        }
        return true
    }

    private fun openSettingsDialog(){
        SettingsDialog().apply {
            setStyle(DialogFragment.STYLE_NORMAL, R.style.Dialog)
            isCancelable = true
            show(supportFragmentManager,"DIALOG_SETTINGS")
        }

    }
}