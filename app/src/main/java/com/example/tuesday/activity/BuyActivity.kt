package com.example.tuesday.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tuesday.R
import com.example.tuesday.databinding.ActivityBuyBinding

class BuyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBuyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuyBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}