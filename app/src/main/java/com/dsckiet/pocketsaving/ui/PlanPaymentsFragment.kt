package com.dsckiet.pocketsaving.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.dsckiet.pocketsaving.R
import com.dsckiet.pocketsaving.ViewPagerAdapter.PlanPaymentViewPager
import com.dsckiet.pocketsaving.databinding.FragmentPlanPaymentsBinding
import com.google.android.material.tabs.TabLayoutMediator


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


        val mytab =binding.planPaymentTabLayout
        val mypager = binding.planPaymentViewPager

        val adapter = PlanPaymentViewPager(requireActivity().supportFragmentManager,lifecycle)

        mypager.adapter = adapter
        TabLayoutMediator(mytab,mypager){
            tab,position->
            when(position){
                0->{
                    tab.text = "Trip Planning"
                }
                1->{
                    tab.text = "Group Divide"
                }
                2->{
                    tab.text = "Loan"
                }
            }
        }.attach()
    }
}