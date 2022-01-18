package com.dsckiet.pocketsaving

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = findNavController(R.id.navHostFragment)

        val bottomnavview = findViewById<BottomNavigationView>(R.id.bottomnavview)
        bottomnavview.background = null
        val myfab = findViewById<FloatingActionButton>(R.id.myfab)

        myfab.setOnClickListener {
            navController.popBackStack()
            navController.navigate(R.id.floatingBtnFragment)

        }

        bottomnavview.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {

                    navController.popBackStack()
                    navController.navigate(R.id.homeFragment)
                }
                R.id.plan -> {

                    navController.popBackStack()
                    navController.navigate(R.id.planPaymentsFragment)

                }
                R.id.profile -> {

                    navController.popBackStack()
                    navController.navigate(R.id.profileFragment)

                }
                R.id.settings -> {

                    navController.popBackStack()
                    navController.navigate(R.id.settingsFragment)

                }

            }
            true
        }

    }

}