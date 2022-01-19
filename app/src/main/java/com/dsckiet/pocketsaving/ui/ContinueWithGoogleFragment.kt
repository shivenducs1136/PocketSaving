package com.dsckiet.pocketsaving.ui

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.dsckiet.pocketsaving.EnterPocketMoney
import com.dsckiet.pocketsaving.MainActivity
import com.dsckiet.pocketsaving.R
import com.dsckiet.pocketsaving.ViewPagerAdapter.CWGViewPagerAdapter
import com.dsckiet.pocketsaving.databinding.FragmentContinueWithGoogleBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import java.util.*


const val RC_SIGN_IN = 100
class ContinueWithGoogleFragment : Fragment() {

    lateinit var binding:FragmentContinueWithGoogleBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContinueWithGoogleBinding.inflate(layoutInflater)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createRequest()
        auth = FirebaseAuth.getInstance()



        binding.btnCreateAccount.setOnClickListener {

            if(isNetworkConnected()){
                signIn()
            }
            else{
                Snackbar.make(requireView(),"Please connect to the Internet.",Snackbar.LENGTH_SHORT).show()
            }
        }

        binding.skipfornow.setOnClickListener {
            val settings = activity?.getSharedPreferences("skip",Context.MODE_PRIVATE)
            val editor = settings?.edit()
            editor?.putBoolean("skipped",true)
            editor?.commit()
            editor?.apply()

            val i = Intent(requireActivity(), EnterPocketMoney::class.java)
            startActivity(i)

        }


        binding.viewPager.adapter = CWGViewPagerAdapter(requireActivity(), requireContext())
        TabLayoutMediator(binding.pageIndicator, binding.viewPager) { _, _ -> }.attach()
        binding.viewPager.offscreenPageLimit = 1

        binding.viewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                var currpos = position
                val handler = Handler()

                val update = Runnable {
                    if (currpos == 3) {
                        currpos = 0
                    }
                    binding.viewPager.setCurrentItem(currpos++, true)
                }


                Timer().schedule(object : TimerTask() {
                    override fun run() {
                        handler.post(update)
                    }
                }, 1000, 2000)
            }
        })
    }
    private fun createRequest() {
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("572218608495-h5la4ub8re2dipk369q9t9qbo6d97d6f.apps.googleusercontent.com")
            .requestEmail()
            .build()


        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(ContentValues.TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
//                binding.load.visibility = View.GONE
                Toast.makeText(requireContext(),"Sign In Failed Please Try Again ", Toast.LENGTH_SHORT).show()
                // Google Sign In failed, update UI appropriately
                Log.w(ContentValues.TAG, "Google sign in failed", e)
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(ContentValues.TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    val i = Intent(requireActivity(), EnterPocketMoney::class.java)
                    startActivity(i)
                    val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return@addOnCompleteListener
                    with (sharedPref.edit()) {
                        putInt("Flag", 0)
                        apply()
                    }
                    val editor = sharedPref.edit()
                    editor.putInt("x", 1)
                    editor.apply()
//                    binding.load.visibility = View.GONE
                    requireActivity().finish()
                } else {
//                    binding.load.visibility = View.GONE
                    Log.w(ContentValues.TAG, "signInWithCredential:failure", task.exception)
                }
            }
    }
    private fun isNetworkConnected(): Boolean {
        val cm = activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null
    }

}