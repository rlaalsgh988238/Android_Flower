package com.example.tuesday.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.tuesday.fragment.CalendarFragment
import com.example.tuesday.fragment.DetailScheduleFragment
import com.example.tuesday.fragment.MainPageFragment
import com.example.tuesday.fragment.ScheduleFragment

class CalendarVIewPagerAdapter (fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    val scheduleFragment = ScheduleFragment()
    val detailScheduleFragment = DetailScheduleFragment()


    private val menuFragmentList = listOf<Fragment>(
        scheduleFragment, detailScheduleFragment
    )

    override fun getItemCount(): Int {
        return menuFragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return menuFragmentList[position]
    }
}