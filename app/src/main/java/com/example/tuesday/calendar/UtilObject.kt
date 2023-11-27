package com.example.tuesday.calendar

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.tuesday.fragment.DetailScheduleFragment

object UtilObject {
    lateinit var listeningActivity: UtilListener

    lateinit var currentActivity: AppCompatActivity

    lateinit var scheduleFragment: Fragment
    lateinit var detailScheduleFragment: DetailScheduleFragment

    fun setListener(listener: UtilListener){
        listeningActivity = listener
    }

    fun getCurrentContext(){
        var context = currentActivity
    }
}

interface UtilListener{
    fun scheduleClicked(position: Int)
    fun storeButtonClicked()
}