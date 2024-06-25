package com.example.tuesday.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tuesday.R
import com.example.tuesday.databinding.ActivityPrivacyBinding
import com.example.tuesday.databinding.ActivityPurchase3Binding

class PrivacyActivity : AppCompatActivity() {
    lateinit var binding: ActivityPrivacyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityPrivacyBinding.inflate(layoutInflater)


        binding.backIv.setOnClickListener {
            finish()
        }
        binding.backTv.setOnClickListener {
            finish()
        }

        setContentView(binding.root)
    }
}