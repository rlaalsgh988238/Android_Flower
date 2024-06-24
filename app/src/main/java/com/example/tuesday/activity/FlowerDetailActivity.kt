package com.example.tuesday.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.tuesday.AI.ChatGPT
import com.example.tuesday.AI.RecommendFlowerData
import com.example.tuesday.AI.LikeRecommend
import com.example.tuesday.Data.SavedData
import com.example.tuesday.Object.SavedHandler
import com.example.tuesday.R
import com.example.tuesday.databinding.ActivityBuyBinding
import com.example.tuesday.databinding.ActivityFlowerDetailBinding
import kotlinx.coroutines.flow.flow
import java.util.concurrent.Flow

class FlowerDetailActivity : AppCompatActivity() {
    var eventName: String = ""
    private lateinit var binding: ActivityFlowerDetailBinding
    val chatGPT = ChatGPT()
    lateinit var flowerData: RecommendFlowerData
    var loadStatus = false
    var recommendStatus = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFlowerDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        eventName = intent.getStringExtra("eventName").toString()
        binding.backIv.setOnClickListener(){
            onBackPressed()
        }

        binding.likeIv.setOnClickListener(){
            if (recommendStatus){
                recommendStatus = false
                binding.likeIv.setImageResource(R.drawable.liked_off)
                LikeRecommend.deleteFlowerData(flowerData)
                SavedHandler.deleteSavedList(SavedHandler.makeSavedList(flowerData))
            }
            else{
                if (loadStatus){
                    recommendStatus = true
                    binding.likeIv.setImageResource(R.drawable.liked_on)
                    LikeRecommend.putFlowerData(flowerData)
                    SavedHandler.addSavedList(flowerData)
                }
            }
        }

        binding.purchaseIv.setOnClickListener(){
            // 구매
        }

    }

    override fun onStart() {
        super.onStart()
        startChatGpt()
    }


    private fun startChatGpt() {
        chatGPT.getFlowerData(eventName) { flowerData ->
            flowerData?.let {
                runOnUiThread {
                    setFlower(it)
                }
            } ?: runOnUiThread {
                // 데이터 로드 실패 시 처리
                binding.recommendMessageTv.text = "데이터를 불러오지 못했습니다."
            }
        }
    }

    private fun setFlower(flowerData: RecommendFlowerData){
        this.flowerData = flowerData
        val flowerName = flowerData.flower.name
        val symbolism = flowerData.flower.symbolism
        val description = flowerData.flower.description
        val event = flowerData.event
        val message = flowerData.message
        val flowerEvent = flowerData.flowerEvent

        binding.flowerIv.setImageResource(chatGPT.getFlowerImage(flowerData))

        if (flowerData.flowerEvent){
            binding.eventTv.text = eventName
            binding.flowerNameTv.text = flowerName
            binding.recommendMessageTv.text = message
            binding.flowerInformationTv.text = description
            binding.flowerSymbolTv.text = symbolism
            loadStatus = true
        }
        else{
            binding.parentCl.setBackgroundColor(Color.parseColor("#FFFFFF"))
            binding.likeIv.visibility = View.INVISIBLE
            binding.whenFalseCl.visibility=View.INVISIBLE
            binding.flowerNameTv.text = "기념일이 아니에요!"
            binding.flowerNameTv.gravity = Gravity.CENTER

            binding.purchaseIv.visibility=View.INVISIBLE
            binding.recommendTv.text=message
            binding.recommendMessageTv.visibility=View.INVISIBLE
            binding.eventTv.text = eventName
            binding.eventTv.gravity = Gravity.CENTER

            binding.flowerIv.setImageResource(R.drawable.gpt_false_1)


        }

        binding.purchaseIv.setOnClickListener {
            val intent = Intent(this, PurchaseActivity::class.java)
            startActivity(intent)
        }
    }
}