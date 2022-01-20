package com.dsckiet.pocketsaving.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dsckiet.pocketsaving.R
import com.dsckiet.pocketsaving.databinding.FragmentAddLoanBinding
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

class AddLoanFragment : Fragment() {

    lateinit var binding:FragmentAddLoanBinding
    var positive = true
    var negative = false
    var friendname=""
    var takenorgiven=""
    var date=""
    var loanreason=""
    var loanamount=""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddLoanBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addLoanAddM.visibility= View.INVISIBLE
        binding.addLoanRemove.visibility = View.VISIBLE
        binding.addLoanDate.text  =  SimpleDateFormat.getDateInstance().format(Date())
        binding.addLoanAddM.setOnClickListener {
            binding.addLoanAddM.visibility= View.INVISIBLE
            binding.addLoanRemove.visibility = View.VISIBLE
            positive = false
            negative = true
        }
        binding.addLoanRemove.setOnClickListener {
            binding.addLoanAddM.visibility= View.VISIBLE
            binding.addLoanRemove.visibility = View.INVISIBLE
            positive = true
            negative = false
        }
        binding.addLoanAddBtn.setOnClickListener {
            SendDataToLoanFragment()
        }

    }

    private fun SendDataToLoanFragment() {

        friendname = binding.loaninput.text.toString()
        loanreason = binding.addLoanNameInput.text.toString()
        loanamount = binding.addLoanEnteredAmount.text.toString()
        date = SimpleDateFormat.getDateInstance().format(Date())

        if(!friendname.isNullOrEmpty() && !loanreason.isNullOrEmpty()  && !loanamount.isNullOrEmpty()){
            if(positive){
                takenorgiven="given"
            }
            if(negative){
                takenorgiven = "taken"
            }
            Log.e("friendname",friendname)
            Log.e("loanreason",loanreason)
            Log.e("date",date)
            Log.e("loanamount",loanamount)

            val bundle = Bundle()
            bundle.putString("friendname",friendname)
            bundle.putString("loanreason",loanreason)
            bundle.putString("date",date)
            bundle.putString("takenorgiven",takenorgiven)
            bundle.putString("loanamount",loanamount)
            bundle.putBoolean("qqpassed",true)
            findNavController().navigate(R.id.action_addLoanFragment_to_loanFragment,bundle)
        }else{
            Snackbar.make(requireView(),"Please Enter the Valid Details.", Snackbar.LENGTH_SHORT).show()
        }


    }

}