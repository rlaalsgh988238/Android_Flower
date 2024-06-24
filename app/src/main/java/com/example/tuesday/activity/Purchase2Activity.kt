package com.example.tuesday.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tuesday.R
import com.example.tuesday.databinding.ActivityPurchase2Binding
import com.example.tuesday.databinding.ActivityPurchaseBinding

class Purchase2Activity : AppCompatActivity() {
    lateinit var binding: ActivityPurchase2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPurchase2Binding.inflate(layoutInflater)
        binding.payIv.setOnClickListener {
            val intent =
                Intent(this, Purchase3Activity::class.java) //fragment라서 activity intent와는 다른 방식
            finish()
            startActivity(intent)
        }

        binding.backIv.setOnClickListener {
            finish()
        }
        binding.backTv.setOnClickListener {
            finish()
        }
        setContentView(binding.root)
    }
}