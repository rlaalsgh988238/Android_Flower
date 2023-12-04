package com.example.tuesday.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tuesday.MainActivity
import com.example.tuesday.R
import com.example.tuesday.databinding.ActivityBuyBinding

class BuyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBuyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLayout()
    }

    private fun initLayout() {
        //홈으로 돌아가기
        binding.btnKakao.setOnClickListener {
            val intent = Intent(this, BuyConfirmedActivity::class.java)
            startActivity(intent)
        }

        binding.btnNaverpay.setOnClickListener {
            val intent = Intent(this, BuyConfirmedActivity::class.java)
            startActivity(intent)
        }

        binding.btnSamsung.setOnClickListener {
            val intent = Intent(this, BuyConfirmedActivity::class.java)
            startActivity(intent)
        }
    }
}