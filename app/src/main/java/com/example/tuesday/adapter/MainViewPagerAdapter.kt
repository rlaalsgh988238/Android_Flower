package com.example.tuesday.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.tuesday.fragment.BouquetFragment
import com.example.tuesday.fragment.CalendarFragment
import com.example.tuesday.fragment.GiftFragment
import com.example.tuesday.fragment.MainPageFragment
import com.example.tuesday.fragment.UserCalendarFragment
import com.example.tuesday.fragment.UserEventFragment
import com.example.tuesday.fragment.my_page

class MainViewPagerAdapter (fragmentActivity: FragmentActivity, private val email: String?, private val name: String?): FragmentStateAdapter(fragmentActivity){

    private val menuFragmentList = listOf<Fragment>(//지금은 안쓰임
        MainPageFragment(), UserCalendarFragment(), my_page()
    )

    override fun getItemCount(): Int {
        return menuFragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                val fragment = GiftFragment()
                val bundle = Bundle()
                bundle.putString("email", email)
                bundle.putString("name", name)
                fragment.arguments = bundle
                fragment
            }
            1 -> {
                val fragment = UserEventFragment()
                val bundle = Bundle()
                bundle.putString("email", email)
                bundle.putString("name", name)
                fragment.arguments = bundle
                fragment
            }2 -> {
                val fragment = my_page()
                val bundle = Bundle()
                bundle.putString("email", email)
                bundle.putString("name", name)
                fragment.arguments = bundle
                fragment
            }
            else -> throw IllegalStateException("Invalid position")
        }
    }
}