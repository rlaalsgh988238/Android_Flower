package com.example.tuesday.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tuesday.R
import com.example.tuesday.adapter.FlowerListRecyclerAdapter
import com.example.tuesday.databinding.ActivityEditFlowerBinding

class EditFlowerActivity : AppCompatActivity() {
    lateinit var binding: ActivityEditFlowerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditFlowerBinding.inflate(layoutInflater)

        binding.recyclerView.layoutManager = LinearLayoutManager(this).also { it.orientation = LinearLayoutManager.HORIZONTAL }
        binding.recyclerView.adapter = FlowerListRecyclerAdapter()

        setContentView(binding.root)
    }
}