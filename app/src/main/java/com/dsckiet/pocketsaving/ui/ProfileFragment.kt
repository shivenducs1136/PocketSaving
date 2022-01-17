package com.dsckiet.pocketsaving.ui

import android.content.Intent
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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment() {
    private lateinit var binding : FragmentProfileBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var signInAccount: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(this.requireContext())
        if(signInAccount!=null){
            binding.name.text = signInAccount.displayName
            Picasso.with(context)
                .load(signInAccount.photoUrl)
                .into(binding.ppic)
            binding.email.text = signInAccount.email

            databaseReference = FirebaseDatabase.getInstance().getReference("Users")
            val User = User(signInAccount.displayName,signInAccount.familyName,signInAccount.email,signInAccount.photoUrl.toString(),signInAccount.account.toString())
            signInAccount.displayName?.let { databaseReference.child(it).setValue(User) }
        }

        logoutBtn.setOnClickListener {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
            val googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
            googleSignInClient.signOut()
            Toast.makeText(context, "Signout Successfully", Toast.LENGTH_SHORT).show()
//            val i = Intent(this, SplashActivity::class.java)
//            startActivity(i)
            requireActivity().run{
                startActivity(Intent(this, SplashActivity::class.java))
                finish()
            }
        }
    }

}