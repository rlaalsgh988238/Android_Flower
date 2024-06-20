package com.example.tuesday

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tuesday.adapter.MainViewPagerAdapter
import com.example.tuesday.calendar.ScheduleModel
import com.example.tuesday.calendar.UtilListener
import com.example.tuesday.calendar.UtilObject
import com.example.tuesday.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UtilObject.currentActivity = this
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = MainViewPagerAdapter(this)
        binding.viewPager.isUserInputEnabled = false

        var intentStatus = intent.getBooleanExtra("push",false)
        if (intentStatus){
            binding.viewPager.currentItem = 1
        }

        val bottomNavigation = binding.bottomNavigation

        //bottom nav 기본 select를 home으로
        bottomNavigation.setSelectedItemId(R.id.menu_home)
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home ->  binding.viewPager.currentItem = 0
                R.id.menu_calendar -> binding.viewPager.currentItem = 1
                R.id.menu_bouquet -> binding.viewPager.currentItem = 2
            }
            //화면 움직이는 쪽
            true
        }
    }
}