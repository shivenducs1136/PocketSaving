package com.dsckiet.pocketsaving

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
<<<<<<< HEAD
import androidx.navigation.findNavController
=======
import android.widget.Button
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

>>>>>>> f37d7df9934eac8144da6bdd37569372ddf5b764

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
<<<<<<< HEAD
        val navController = findNavController(R.id.navHostFragment)


=======

        fab.setOnClickListener {
            navController.navigate(R.id.floatingBtnFragment)
        }
        val plan = findViewById<Button>(R.id.plan)
        plan.setOnClickListener {
            navController.navigate(R.id.planPaymentsFragment)
        }



>>>>>>> f37d7df9934eac8144da6bdd37569372ddf5b764
    }
}