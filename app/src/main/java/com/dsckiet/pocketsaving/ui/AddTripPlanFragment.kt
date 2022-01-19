package com.dsckiet.pocketsaving.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.dsckiet.pocketsaving.R
import com.dsckiet.pocketsaving.databinding.FragmentAddPlanBinding
import com.dsckiet.pocketsaving.databinding.FragmentAddTripPlanBinding
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

class AddTripPlanFragment : Fragment() {

    lateinit var binding: FragmentAddTripPlanBinding
    var itemTitle=""
    var total_amount=""
    var numberofpeople=""
    var perheadcost=""
    var dateandtime=""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddTripPlanBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tripPlanSubmit.setOnClickListener {
            SendDataToTripPlanningFragment()
        }
    }
    private fun SendDataToTripPlanningFragment() {
        itemTitle = binding.tripTotalItemPurchased.text.toString()
        total_amount = binding.tripTotalAmountSpend.text.toString()
        numberofpeople = binding.tripTotalNumberOfPerson.text.toString()
        perheadcost = (total_amount.toInt()/numberofpeople.toInt()).toString()
        dateandtime = SimpleDateFormat.getDateInstance().format(Date())

        val sharedPref = activity?.getSharedPreferences("mt", Context.MODE_PRIVATE) ?: return
        var updatedamount = sharedPref.getString("PocketMoney", "0")
        updatedamount = (updatedamount?.toInt()?.minus(perheadcost.toInt())).toString()
        val settings = activity?.getSharedPreferences("mt",Context.MODE_PRIVATE)
        val editor = settings?.edit()
        editor?.putString("PocketMoney", updatedamount)
        editor?.commit()
        editor?.apply()
     if(!itemTitle.isNullOrEmpty()&& !total_amount.isNullOrEmpty() && !numberofpeople.isNullOrEmpty()
         && !perheadcost.isNullOrEmpty()&&!dateandtime.isNullOrEmpty()
     ){
         val bundle = Bundle()
         bundle.putString("itemTitle",itemTitle)
         bundle.putString("total_amount",total_amount)
         bundle.putString("numberofpeople",numberofpeople)
         bundle.putString("perheadcost",perheadcost)
         bundle.putString("dateandtime",dateandtime)
         bundle.putBoolean("ischecked",true)
         findNavController().navigate(R.id.action_addTripPlanFragment_to_tripPlanning,bundle)


     }else{
         Snackbar.make(requireView(),"All fields are required",Snackbar.LENGTH_SHORT).show()
     }


    }
}