package com.example.tuesday.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tuesday.MainActivity
import com.example.tuesday.R
import com.example.tuesday.databinding.ActivityPurchase2Binding
import com.example.tuesday.databinding.ActivityPurchase3Binding

class Purchase3Activity : AppCompatActivity() {
    lateinit var binding: ActivityPurchase3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPurchase3Binding.inflate(layoutInflater)
        binding.backToHomeIv.setOnClickListener {
            finish()
        }
        setContentView(binding.root)
    }
}