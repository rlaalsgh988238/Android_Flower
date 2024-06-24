package com.example.tuesday.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.tuesday.Data.favorData
import com.example.tuesday.R
import com.example.tuesday.adapter.SelectFavorAdapter
import com.example.tuesday.databinding.ActivityPurchaseBinding
import com.example.tuesday.databinding.ActivityReselectBinding

class PurchaseActivity : AppCompatActivity() {
    lateinit var binding: ActivityPurchaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityPurchaseBinding.inflate(layoutInflater)
        binding.keepIv.setOnClickListener {
            val intent =
                Intent(this, Purchase2Activity::class.java) //fragment라서 activity intent와는 다른 방식
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