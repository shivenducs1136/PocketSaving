package com.dsckiet.pocketsaving.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.dsckiet.pocketsaving.R
import com.dsckiet.pocketsaving.databinding.FragmentFloatingBtnBinding
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*
import androidx.fragment.app.FragmentManager


class FloatingBtnFragment : Fragment() {

    lateinit var binding : FragmentFloatingBtnBinding
    var itemname=""
    var EnteredAmount=""
    var dateandtime=""
    var note=""
    var positive = false
    var amountadded = ""
    var removedamount=""
    var negative = true
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding  = FragmentFloatingBtnBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addM.visibility= View.INVISIBLE
        binding.remove.visibility = View.VISIBLE
        binding.date.text  =  SimpleDateFormat.getDateInstance().format(Date())
        binding.addM.setOnClickListener {
            binding.addM.visibility= View.INVISIBLE
            binding.remove.visibility = View.VISIBLE
            positive = false
            negative = true
        }
        binding.remove.setOnClickListener {
            binding.addM.visibility= View.VISIBLE
            binding.remove.visibility = View.INVISIBLE
            positive = true
            negative = false
        }
        binding.addBtn.setOnClickListener {
            SendDataToHomeFragment()
        }

    }

    private fun SendDataToHomeFragment() {
        itemname = binding.nameInput.text.toString()
        EnteredAmount = binding.enteredAmount.text.toString()
        dateandtime = SimpleDateFormat.getDateInstance().format(Date())
        note = binding.addnote.text.toString()

        val sharedPref = activity?.getSharedPreferences("mt", Context.MODE_PRIVATE) ?: return
        var updatedamount = sharedPref.getString("PocketMoney", "0")

        if(note.isNullOrEmpty()){
            note = "Not Available"
        }
        if(!itemname.isNullOrEmpty() && !dateandtime.isNullOrEmpty()  && !EnteredAmount.isNullOrEmpty()){
            if(positive){

                amountadded = EnteredAmount
                removedamount="0"
                updatedamount= (updatedamount?.toInt()?.plus(EnteredAmount.toInt())).toString()
                EnteredAmount = updatedamount.toString()

                val settings = activity?.getSharedPreferences("mt",Context.MODE_PRIVATE)
                val editor = settings?.edit()
                editor?.putString("PocketMoney", EnteredAmount)
                editor?.commit()
                editor?.apply()
            }
            if(negative){
                amountadded="0"
                removedamount = EnteredAmount
                updatedamount= (updatedamount?.toInt()?.minus(EnteredAmount.toInt())).toString()
                EnteredAmount = updatedamount.toString()

                val settings = activity?.getSharedPreferences("mt",Context.MODE_PRIVATE)
                val editor = settings?.edit()
                editor?.putString("PocketMoney", EnteredAmount)
                editor?.commit()
                editor?.apply()
            }
            Log.e("itemname",itemname)
            Log.e("date",dateandtime)
            Log.e("note",note)
            Log.e("EnteredAmount",EnteredAmount)

            val bundle = Bundle()
            bundle.putString("itemname",itemname)
            bundle.putString("date",dateandtime)
            bundle.putString("note",note)
            bundle.putString("EnteredAmount",EnteredAmount)
            bundle.putString("Addedamount",amountadded)
            bundle.putString("removedAmount",removedamount)
            bundle.putBoolean("passed",true)
            findNavController().navigate(R.id.action_floatingBtnFragment_to_homeFragment,bundle)
        }else{
            Snackbar.make(requireView(),"Please Enter the Valid Details.",Snackbar.LENGTH_SHORT).show()
        }


    }

}