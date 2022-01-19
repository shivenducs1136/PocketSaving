package com.dsckiet.pocketsaving.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.dsckiet.pocketsaving.R
import com.dsckiet.pocketsaving.databinding.FragmentGroupDivideBinding
import com.google.android.material.slider.Slider


class GroupDivideFragment : Fragment() {

    lateinit var binding:FragmentGroupDivideBinding
    var amount=0
    var calcamnt =0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGroupDivideBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPref = activity?.getSharedPreferences("mt", Context.MODE_PRIVATE) ?: return
        val totalamount = sharedPref.getString("PocketMoney", "0")
            Log.e("0","0$totalamount")
        binding.slider.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: Slider) {
                amount= binding.slider.value.toInt()
                calcamnt = ((totalamount?.toInt()!!*amount)/100)
                binding.gdAmount.text = "${calcamnt}"
                Log.e("1","1")
                // Responds to when slider's touch event is being started
            }

            override fun onStopTrackingTouch(slider: Slider) {
                amount= binding.slider.value.toInt()
                calcamnt = ((totalamount?.toInt()!!*amount)/100)
                binding.gdAmount.text = "${calcamnt}"
                Log.e("2","2")
                // Responds to when slider's touch event is being stopped
            }
        })
        binding.slider.addOnChangeListener { slider, value, fromUser ->
            // Responds to when slider's value is changed
            amount= binding.slider.value.toInt()
            calcamnt = ((totalamount?.toInt()!!*amount)/100)
            binding.gdAmount.text = "${calcamnt}"
            Log.e("3","3")
        }

        binding.submit.setOnClickListener {
            Log.e("4","4")

            Toast.makeText(requireContext(),"Warning set for Rs.$calcamnt ",Toast.LENGTH_SHORT).show()
            val realamnt = totalamount?.toFloat()!! - calcamnt
            val settings = activity?.getSharedPreferences("gd",Context.MODE_PRIVATE)
            val editor = settings?.edit()
            editor?.putString("warning", realamnt.toString())
            editor?.commit()
            editor?.apply()


        }

    }

}