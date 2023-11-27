package com.example.tuesday

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tuesday.adapter.MainViewPagerAdapter
import com.example.tuesday.calendar.ScheduleModel
import com.example.tuesday.calendar.UtilListener
import com.example.tuesday.calendar.UtilObject
import com.example.tuesday.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UtilObject.currentActivity = this
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = MainViewPagerAdapter(this)

        var intentStatus = intent.getBooleanExtra("push",false)
        if (intentStatus){
            binding.viewPager.currentItem = 2
        }



    }
}