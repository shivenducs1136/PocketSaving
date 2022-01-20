package com.dsckiet.pocketsaving.ViewPagerAdapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dsckiet.pocketsaving.ui.GroupDivideFragment
import com.dsckiet.pocketsaving.ui.LoanFragment
import com.dsckiet.pocketsaving.ui.TripPlanning

class PlanPaymentViewPager(fragmentmanager:FragmentManager,lifecycle:Lifecycle): FragmentStateAdapter(fragmentmanager,lifecycle) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->{
                GroupDivideFragment()
            }
            1->{
                TripPlanning()
            }
            2->{
                LoanFragment()
            }
            else->{
                Fragment()
            }
        }

    }
}