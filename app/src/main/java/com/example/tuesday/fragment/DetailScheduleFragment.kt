package com.example.tuesday.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tuesday.MainActivity
import com.example.tuesday.R
import com.example.tuesday.activity.EditFlowerActivity
import com.example.tuesday.calendar.Schedule
import com.example.tuesday.calendar.ScheduleModel
import com.example.tuesday.calendar.UtilListener
import com.example.tuesday.calendar.UtilObject
import com.example.tuesday.databinding.FragmentDetailScheduleBinding


class DetailScheduleFragment : Fragment() {
    lateinit var binding: FragmentDetailScheduleBinding
    lateinit var meaning: String
    lateinit var title: String
    var schedulePosition = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mean.text = meaning
        binding.scheduleName.text = title

        binding.btnEditFlower.setOnClickListener {
            val intent = Intent(UtilObject.currentActivity, EditFlowerActivity::class.java)
            intent.putExtra("schedulePosition",schedulePosition)
            startActivity(intent)
        }
        binding.backPress.setOnClickListener {
            UtilObject.listeningActivity.backButtonClicked()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailScheduleBinding.inflate(layoutInflater)
        return binding.root
    }

    fun setUi(position: Int){
        schedulePosition = position
        meaning = ScheduleModel.schedule[position].flower[0].flowerMeaning
        var scheduleName = ScheduleModel.schedule[position].scheduleName
        var D_day = ScheduleModel.schedule[position].D_day
        makeScheduleName(scheduleName, D_day)
    }

    private fun makeScheduleName(scheduleName: String, D_day: Int){
        title = "D-${D_day} $scheduleName"
    }

}