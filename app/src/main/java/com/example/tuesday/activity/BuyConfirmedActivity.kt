package com.example.tuesday.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tuesday.MainActivity
import com.example.tuesday.R
import com.example.tuesday.adapter.FlowerListRecyclerAdapter
import com.example.tuesday.databinding.ActivityBuyConfirmedBinding
import com.example.tuesday.databinding.ActivityEditFlowerBinding

class BuyConfirmedActivity : AppCompatActivity() {
    lateinit var binding: ActivityBuyConfirmedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuyConfirmedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLayout()
    }

    private fun initLayout() {
        //홈으로 돌아가기
        binding.btnGotoHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}