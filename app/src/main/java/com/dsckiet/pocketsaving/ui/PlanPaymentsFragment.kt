package com.dsckiet.pocketsaving.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dsckiet.pocketsaving.R
import com.dsckiet.pocketsaving.databinding.FragmentPlanPaymentsBinding


class PlanPaymentsFragment : Fragment() {

    lateinit var binding:FragmentPlanPaymentsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlanPaymentsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tripPlanning.setOnClickListener {
            findNavController().navigate(R.id.action_planPaymentsFragment_to_tripPlanning)
        }
        binding.groupDivider.setOnClickListener {
            findNavController().navigate(R.id.action_planPaymentsFragment_to_groupDivideFragment)
        }
        binding.loan.setOnClickListener {
            findNavController().navigate(R.id.action_planPaymentsFragment_to_loanFragment)
        }
    }
}