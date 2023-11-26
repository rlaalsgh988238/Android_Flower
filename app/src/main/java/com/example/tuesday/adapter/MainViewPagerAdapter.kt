package com.example.tuesday.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.tuesday.fragment.CalendarFragment
import com.example.tuesday.fragment.MainPageFragment

class MainViewPagerAdapter (fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity){

    private val menuFragmentList = listOf<Fragment>(
        MainPageFragment(), CalendarFragment()
    )

    override fun getItemCount(): Int {
        return menuFragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return menuFragmentList[position]
    }
}