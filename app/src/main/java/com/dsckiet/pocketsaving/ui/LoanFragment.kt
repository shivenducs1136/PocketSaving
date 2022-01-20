package com.dsckiet.pocketsaving.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dsckiet.pocketsaving.Adapter.LoanAdapter
import com.dsckiet.pocketsaving.Adapter.PocketSavingAdapter
import com.dsckiet.pocketsaving.R
import com.dsckiet.pocketsaving.ViewModel.LoanViewModel
import com.dsckiet.pocketsaving.ViewModel.PocketSavingViewModel
import com.dsckiet.pocketsaving.databinding.FragmentLoanBinding
import com.dsckiet.pocketsaving.entity.LoanEntity
import com.dsckiet.pocketsaving.entity.PocketSavingEntity


class LoanFragment : Fragment() {


    lateinit var binding:FragmentLoanBinding
    private lateinit var viewModel : LoanViewModel
    var friendname=""
    var takenorgiven=""
    var date=""
    var loanreason=""
    var loanamount=""
    var callAddtoRoom = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentLoanBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.homeLottie.visibility = View.GONE
        val adapter = LoanAdapter(requireContext(),this)
        val recyclerView = binding.myloanrecview
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(
            LoanViewModel::class.java)
        viewModel.allLoan.observe(viewLifecycleOwner, Observer {list ->
            list?.let{
                if(it.isEmpty()){
//                    binding.homeLottie.visibility = View.VISIBLE
                }else{
//                    binding.homeLottie.visibility = View.GONE
                    adapter.updateSubjectList(it)
                }

            }
        })
        val bundle = this.arguments
        if(bundle!=null){
            callAddtoRoom = bundle.getBoolean("qqpassed")
        }
        if(callAddtoRoom){
            addtoroom()
        }
        binding.addloanbtn.setOnClickListener {
            findNavController().navigate(R.id.action_planPaymentsFragment_to_addLoanFragment)
        }

    }

    private fun addtoroom() {

        var bundleid = this.arguments
        if(bundleid!=null){
            friendname = bundleid.getString("friendname").toString()
            date = bundleid.getString("date").toString()
            loanamount = bundleid.getString("loanamount").toString()
            loanreason = bundleid.getString("loanreason").toString()
            takenorgiven = bundleid.getString("takenorgiven").toString()
        }
        if(!friendname.isNullOrEmpty() && !date.isNullOrEmpty() && !loanamount.isNullOrEmpty() &&!loanreason.isNullOrEmpty() &&
            !takenorgiven.isNullOrEmpty() ) {
            viewModel.insertLoan(
                LoanEntity(loanamount,friendname,takenorgiven,date,loanreason)
            )
        }
    }
    fun delete(loanEntity: LoanEntity){
            viewModel.deleteLoan(loanEntity)
            Toast.makeText(requireContext(),"Deleted", Toast.LENGTH_SHORT).show()
    }

}