package com.example.tuesday.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tuesday.R
import com.example.tuesday.adapter.CalendarVIewPagerAdapter
import com.example.tuesday.calendar.UtilListener
import com.example.tuesday.calendar.UtilObject
import com.example.tuesday.databinding.FragmentCalendarBinding

class CalendarFragment : Fragment(), UtilListener {
    private lateinit var binding: FragmentCalendarBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUI()


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalendarBinding.inflate(layoutInflater)
        UtilObject.setListener(this)

        return binding.root
    }

    override fun scheduleClicked(position: Int) {
        binding.viewPager.currentItem = 1
        setUI()
    }


    override fun backButtonClicked() {
        binding.viewPager.currentItem = 0
    }

    private fun setUI(){
        val adapter = CalendarVIewPagerAdapter(requireActivity())
        UtilObject.scheduleFragment = adapter.scheduleFragment
        UtilObject.detailScheduleFragment = adapter.detailScheduleFragment
        binding.viewPager.adapter = adapter
        binding.viewPager.isUserInputEnabled = false
    }
}