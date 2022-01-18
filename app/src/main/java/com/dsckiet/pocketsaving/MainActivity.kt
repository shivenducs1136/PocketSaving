package com.dsckiet.pocketsaving

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = findNavController(R.id.navHostFragment)



        fabtn.setOnClickListener {
            navController.navigate(R.id.floatingBtnFragment)
        }
        val plan = findViewById<Button>(R.id.plan)
        plan.setOnClickListener {
            navController.navigate(R.id.planPaymentsFragment)
        }


    }
}