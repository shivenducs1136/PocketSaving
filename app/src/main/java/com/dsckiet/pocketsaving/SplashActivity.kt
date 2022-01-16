package com.dsckiet.pocketsaving

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.FragmentContainerView
import com.dsckiet.pocketsaving.ui.ContinueWithGoogleFragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var signInAccount: GoogleSignInAccount
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        firebaseAuth = FirebaseAuth.getInstance()
        val cont = findViewById<FragmentContainerView>(R.id.fragmentContainerView)
        if(GoogleSignIn.getLastSignedInAccount(this) == null){
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,
                ContinueWithGoogleFragment()
            )
        }
        else
            splashfun()
    }
    fun splashfun() {
        Handler().postDelayed({
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }, 3000)

    }
}