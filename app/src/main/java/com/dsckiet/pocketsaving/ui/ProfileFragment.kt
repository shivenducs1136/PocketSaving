package com.dsckiet.pocketsaving.ui

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import android.view.ViewGroup
import android.widget.Toast
import com.dsckiet.pocketsaving.R
import com.dsckiet.pocketsaving.SplashActivity
import com.dsckiet.pocketsaving.databinding.FragmentProfileBinding
import com.dsckiet.pocketsaving.dataclass.User
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso


class ProfileFragment : Fragment() {
    private lateinit var binding : FragmentProfileBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
            binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()
        var signInAccount: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(this.requireContext())
        val sharedPref = activity?.getSharedPreferences("skip", Context.MODE_PRIVATE) ?: return
        val isskipped = sharedPref.getBoolean("skipped", false)
        if(signInAccount!=null && !isskipped){
            binding.name.text = signInAccount.displayName
            Picasso.with(context)
                .load(signInAccount.photoUrl)
                .into(binding.ppic)
            binding.email.text = signInAccount.email

            databaseReference = FirebaseDatabase.getInstance().getReference("Users")
            val User = User(signInAccount.displayName,signInAccount.familyName,signInAccount.email,signInAccount.photoUrl.toString(),signInAccount.account.toString())
            databaseReference.child(signInAccount.displayName.toString()).setValue(User)
            signInAccount.displayName?.let { databaseReference.child(it).setValue(User) }
        }
        binding.logoutBtn.setOnClickListener {
            val sharedPref = activity?.getSharedPreferences("skip", Context.MODE_PRIVATE) ?: return@setOnClickListener
            val isskipped = sharedPref.getBoolean("skipped", false)
            if(isNetworkConnected()){
                if(!isskipped){
                    firebaseAuth.signOut()
                    val settings = activity?.getSharedPreferences("skip",Context.MODE_PRIVATE)
                    val edito = settings?.edit()
                    edito?.putBoolean("skipped",false)
                    edito?.commit()
                    edito?.apply()
                    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
                    val googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
                    googleSignInClient.signOut()

                    Toast.makeText(context, "Signout Successfully", Toast.LENGTH_SHORT).show()
                    val i = Intent(requireContext(), SplashActivity::class.java)
                    startActivity(i)
                    requireActivity().finish()
                }
                else{
                    Snackbar.make(requireView(),"Please login first.",Snackbar.LENGTH_SHORT).show()

                }
            }
            else{
                Snackbar.make(requireView(),"Please check your Internet Connection.",Snackbar.LENGTH_SHORT).show()
            }
        }
        binding.loginBtn.setOnClickListener {
            if(signInAccount==null){
                val settings = activity?.getSharedPreferences("skip",Context.MODE_PRIVATE)
                val editor = settings?.edit()
                editor?.putBoolean("skipped",false)
                editor?.commit()
                editor?.apply()
                val i = Intent(requireContext(), SplashActivity::class.java)
                startActivity(i)
                requireActivity().finish()
            }
            else{
                Snackbar.make(requireView(),"Please logout to login.",Snackbar.LENGTH_SHORT).show()
            }
        }
    }
    private fun isNetworkConnected(): Boolean {
        val cm = activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null
    }

}