package com.example.tuesday.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
        flowerListAdapter.setInfo(binding, this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this).also { it.orientation = LinearLayoutManager.HORIZONTAL }
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
        Log.d("change","change")
        when(num){
            0 -> {
                binding.combined.setImageResource(R.drawable.flower_combine1)
                num++
            }
            1 -> {
                binding.combined.setImageResource(R.drawable.sdfsdf)
                num++
            }
            2 -> {
                binding.combined.setImageResource(R.drawable.flower_combined)
                num++
            }
            else -> {
                binding.combined.setImageResource(R.drawable.fdsfd)
                num = 0
            }
        }

    }
}

interface FlowerSelectListener{
    fun flowerSelected()
}