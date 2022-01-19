package com.dsckiet.pocketsaving.ui

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dsckiet.pocketsaving.R
import com.dsckiet.pocketsaving.databinding.FragmentHomeBinding
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dsckiet.pocketsaving.Adapter.PocketSavingAdapter
import com.dsckiet.pocketsaving.ViewModel.PocketSavingViewModel
import com.dsckiet.pocketsaving.entity.PocketSavingEntity
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.database.DatabaseReference


class HomeFragment : Fragment() {

    lateinit var binding : FragmentHomeBinding
    private lateinit var viewModel : PocketSavingViewModel
    lateinit var database : DatabaseReference
    lateinit var currsub:PocketSavingEntity
    var itemname=""
    var totalamount=""
    var dateandtime=""
    var note=""
    var positive = true
    var amountadded = ""
    var removedamount=""
    var callAddtoRoom=false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeLottie.visibility = View.GONE
        val adapter = PocketSavingAdapter(requireContext(),this)
        val recyclerView = binding.myrecview
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(
            PocketSavingViewModel::class.java)
        viewModel.allSubjects.observe(viewLifecycleOwner, Observer {list ->
            list?.let{
                if(it.isEmpty()){
                    binding.homeLottie.visibility = View.VISIBLE
                }else{
                    binding.homeLottie.visibility = View.GONE
                    adapter.updateSubjectList(it)
                }

            }
        })
        val sharedPref = activity?.getSharedPreferences("mt",Context.MODE_PRIVATE) ?: return
        val totalamount = sharedPref.getString("PocketMoney", "0")
        binding.pocketmoneyAmount.text = totalamount.toString()
        val bundle = this.arguments
        if(bundle!=null){
            callAddtoRoom = bundle.getBoolean("passed")
        }
        if(callAddtoRoom){
            addtoroom()
        }
    }

    fun addtoroom() {


        var bundleid = this.arguments
        var amountleft=""

        if(bundleid!=null){
         itemname = bundleid.getString("itemname").toString()
            dateandtime = bundleid.getString("date").toString()
         note = bundleid.getString("note").toString()
         amountleft = bundleid.getString("EnteredAmount").toString()
            amountadded = bundleid.getString("Addedamount").toString()
            removedamount = bundleid.getString("removedAmount").toString()
        }
        val sharedPref = activity?.getSharedPreferences("mt", Context.MODE_PRIVATE) ?: return
        totalamount = sharedPref.getString("PocketMoney", "0").toString()
        val sharedPrefy = activity?.getSharedPreferences("gd", Context.MODE_PRIVATE) ?: return
        val totay = sharedPrefy.getString("warning", "0")
        if(totay!="0")
        checkWarning(amountleft)

        if(!itemname.isNullOrEmpty() && !dateandtime.isNullOrEmpty() && !note.isNullOrEmpty() &&!totalamount.isNullOrEmpty() &&
            !amountadded.isNullOrEmpty() &&!removedamount.isNullOrEmpty()&&!amountleft.isNullOrEmpty() ) {
            viewModel.insertFriend(PocketSavingEntity(removedamount,amountleft,totalamount,amountadded,
                dateandtime,itemname,"all",note))
        }
    }

    private fun checkWarning(amountleft: String) {
        val sharedPref = activity?.getSharedPreferences("gd", Context.MODE_PRIVATE) ?: return
        val totalamount = sharedPref.getString("warning", "0")

        if(totalamount?.toFloat()!! >=amountleft.toFloat()){
            showWarning()
        }
    }

    private fun showWarning() {
        val builder1 = AlertDialog.Builder(context)
        builder1.setMessage("You are running out of money.")
        builder1.setCancelable(true)

        builder1.setPositiveButton(
            "Ok"
        ) { dialog, id ->

            val settings = activity?.getSharedPreferences("gd",Context.MODE_PRIVATE)
            val editor = settings?.edit()
            editor?.putString("warning", "0")
            editor?.commit()
            editor?.apply()
            Toast.makeText(requireContext(),"Alarm turned off.",Toast.LENGTH_SHORT).show()
            dialog.cancel()

        }

        val alert11 = builder1.create()
        alert11.show()
    }
}