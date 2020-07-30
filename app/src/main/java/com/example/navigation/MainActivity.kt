package com.example.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController

// Main Host
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navControllr: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navControllr = Navigation.findNavController(this,R.id.my_nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(navControllr.graph)
        setupActionBarWithNavController(navControllr, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navControllr.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}