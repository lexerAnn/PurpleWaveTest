package com.purlewave.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.purlewave.R
import com.purlewave.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView = findViewById<BottomNavigationView>(R.id.nav_view)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(navView, navController)

//        val navView = findViewById<BottomNavigationView>(R.id.nav_view)
//
//        val navControllerFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        navController = navControllerFragment.navController
//
//        val inflater = navController.navInflater
//        val graph = inflater.inflate(R.navigation.mobile_navigation)
//
//        navControllerFragment.navController.graph = graph
//
//        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
//        NavigationUI.setupWithNavController(navView, navController)


    }
}