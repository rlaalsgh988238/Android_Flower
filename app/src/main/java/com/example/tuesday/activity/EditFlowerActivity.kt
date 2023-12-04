package com.example.tuesday.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tuesday.R
import com.example.tuesday.adapter.FlowerListRecyclerAdapter
import com.example.tuesday.calendar.ScheduleModel
import com.example.tuesday.databinding.ActivityEditFlowerBinding

class EditFlowerActivity : AppCompatActivity(), FlowerSelectListener {
    lateinit var binding: ActivityEditFlowerBinding
    var schedulePosition: Int = 0
    private val flowerListAdapter = FlowerListRecyclerAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditFlowerBinding.inflate(layoutInflater)

        schedulePosition = intent.getIntExtra("schedulePosition", 0)
        ScheduleModel.schedule[schedulePosition]
        flowerListAdapter.setSchedulePosition(schedulePosition)
        flowerListAdapter.listeningActivity = this

        binding.recyclerView.layoutManager = LinearLayoutManager(this).also { it.orientation = LinearLayoutManager.HORIZONTAL }
        flowerListAdapter
        binding.recyclerView.adapter = flowerListAdapter

        binding.btnStore.setOnClickListener {
            onBackPressed()
        }

        binding.btnBuy.setOnClickListener {
            val intent = Intent(this, BuyActivity::class.java)
            startActivity(intent)
        }

        setContentView(binding.root)
    }

    var num = 0
    override fun flowerSelected() {
        binding.combined.setImageResource(R.drawable.flower_combine1)
    }
}

interface FlowerSelectListener{
    fun flowerSelected()
}