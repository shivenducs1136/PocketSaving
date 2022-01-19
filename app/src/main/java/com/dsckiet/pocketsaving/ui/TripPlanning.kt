package com.dsckiet.pocketsaving.ui

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dsckiet.pocketsaving.Adapter.PocketSavingAdapter
import com.dsckiet.pocketsaving.Adapter.TripAdapter
import com.dsckiet.pocketsaving.R
import com.dsckiet.pocketsaving.ViewModel.PocketSavingViewModel
import com.dsckiet.pocketsaving.ViewModel.TripViewModel
import com.dsckiet.pocketsaving.databinding.FragmentTripPlanningBinding
import com.dsckiet.pocketsaving.entity.PocketSavingEntity
import com.dsckiet.pocketsaving.entity.TripEntity


class TripPlanning : Fragment() {


    lateinit var binding:FragmentTripPlanningBinding
    private lateinit var viewModel : TripViewModel
    var itemTitle=""
    var total_amount=""
    var numberofpeople=""
    var perheadcost=""
    var dateandtime=""
    var isadding=false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTripPlanningBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = TripAdapter(requireContext(),this)
        val recyclerView = binding.tripplanningrecview
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(
            TripViewModel::class.java)
        viewModel.allTrips.observe(viewLifecycleOwner, Observer {list ->

            list?.let{
                adapter.updateSubjectList(it)
            }
        })

        binding.tripPlanningFab.setOnClickListener {
            findNavController().navigate(R.id.action_tripPlanning_to_addTripPlanFragment)
        }

        val bundle = this.arguments
        if(bundle!=null){
            isadding = bundle.getBoolean("ischecked")
        }
        if(isadding){
            AddTripToRoom()
        }

    }

    private fun AddTripToRoom() {
        isadding = false
        var bundleid = this.arguments
        if(bundleid!=null){
            itemTitle = bundleid.getString("itemTitle").toString()
            dateandtime = bundleid.getString("dateandtime").toString()
            total_amount = bundleid.getString("total_amount").toString()
            numberofpeople = bundleid.getString("numberofpeople").toString()
            perheadcost = bundleid.getString("perheadcost").toString()
        }

        if(!itemTitle.isNullOrEmpty() && !dateandtime.isNullOrEmpty() && !total_amount.isNullOrEmpty() &&!numberofpeople.isNullOrEmpty() &&
            !perheadcost.isNullOrEmpty() ) {
            viewModel.inserttrip(
                TripEntity(numberofpeople,total_amount,perheadcost,itemTitle,dateandtime,"trip","note not avail")
            )
        }
    }

}