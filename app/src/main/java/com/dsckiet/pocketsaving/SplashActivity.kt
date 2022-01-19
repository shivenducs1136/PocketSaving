package com.dsckiet.pocketsaving

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.FragmentContainerView
import com.dsckiet.pocketsaving.ui.ContinueWithGoogleFragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import android.preference.PreferenceManager

import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.snackbar.Snackbar


class SplashActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var signInAccount: GoogleSignInAccount
    var isskipped=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        firebaseAuth = FirebaseAuth.getInstance()

        val cont = findViewById<FragmentContainerView>(R.id.fragmentContainerView)
        val lottieview = findViewById<LottieAnimationView>(R.id.mylottie)
        val text = findViewById<TextView>(R.id.pocketsavingtext)
        lottieview.visibility = View.GONE
        text.visibility = View.GONE
        cont.visibility = View.GONE
        val sharedPref = this?.getSharedPreferences("skip",Context.MODE_PRIVATE) ?: return
        isskipped = sharedPref.getBoolean("skipped", false)
        if(isNetworkConnected()){
            Log.e("user",firebaseAuth.currentUser.toString())
            if (firebaseAuth.currentUser == null && !isskipped) {
                lottieview.visibility = View.GONE
                text.visibility = View.GONE
                cont.visibility = View.VISIBLE
                supportFragmentManager.beginTransaction().replace(
                    R.id.fragmentContainerView,
                    ContinueWithGoogleFragment()
                )
            } else {
                lottieview.visibility = View.VISIBLE
                text.visibility = View.VISIBLE
                cont.visibility = View.GONE
                splashfun()
            }
        }
        else{
            Toast.makeText(this,"Please check your Internet Connection.",Toast.LENGTH_SHORT).show()
        }
    }
    fun splashfun() {

            Handler().postDelayed({
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
                finish()
            }, 3000)
    }

    private fun isNetworkConnected(): Boolean {
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null
    }


}