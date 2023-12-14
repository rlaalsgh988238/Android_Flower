package com.example.tuesday.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tuesday.R
import com.example.tuesday.databinding.FragmentMainPageBinding
import com.google.android.material.slider.Slider
import com.google.android.material.slider.Slider.OnSliderTouchListener

class MainPageFragment : Fragment() {
    lateinit var binding: FragmentMainPageBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bookmark1.visibility = View.GONE
        var status1 = false
        binding.flower1.setOnClickListener {
            if (status1){
                status1 = false
                binding.bookmark1.visibility = View.GONE
            }
            else{
                status1 = true
                binding.bookmark1.visibility = View.VISIBLE
            }
        }

        binding.bookmark2.visibility = View.GONE
        var status2 = false
        binding.flower2.setOnClickListener {
            if (status2){
                status2 = false
                binding.bookmark2.visibility = View.GONE
            }
            else{
                status2 = true
                binding.bookmark2.visibility = View.VISIBLE
            }
        }
        binding.bookmark3.visibility = View.GONE
        var status3 = false
        binding.flower3.setOnClickListener {
            if (status3){
                status3 = false
                binding.bookmark3.visibility = View.GONE
            }
            else{
                status3 = true
                binding.bookmark3.visibility = View.VISIBLE
            }
        }
        binding.bookmark4.visibility = View.GONE
        var status4 = false
        binding.flower4.setOnClickListener {
            if (status4){
                status4 = false
                binding.bookmark4.visibility = View.GONE
            }
            else{
                status4 = true
                binding.bookmark4.visibility = View.VISIBLE
            }
        }
        binding.bookmark5.visibility = View.GONE
        var status5 = false
        binding.flower5.setOnClickListener {
            if (status5){
                status5 = false
                binding.bookmark5.visibility = View.GONE
            }
            else{
                status5 = true
                binding.bookmark5.visibility = View.VISIBLE
            }
        }
        binding.bookmark6.visibility = View.GONE
        var status6 = false
        binding.flower6.setOnClickListener {
            if (status6){
                status6 = false
                binding.bookmark6.visibility = View.GONE
            }
            else{
                status6 = true
                binding.bookmark6.visibility = View.VISIBLE
            }
        }
        binding.bookmark7.visibility = View.GONE
        var status7 = false
        binding.flower7.setOnClickListener {
            if (status7){
                status7 = false
                binding.bookmark7.visibility = View.GONE
            }
            else{
                status7 = true
                binding.bookmark7.visibility = View.VISIBLE
            }
        }
        binding.bookmark8.visibility = View.GONE
        var status8 = false
        binding.flower8.setOnClickListener {
            if (status8){
                status8 = false
                binding.bookmark8.visibility = View.GONE
            }
            else{
                status8 = true
                binding.bookmark8.visibility = View.VISIBLE
            }
        }
        binding.bookmark9.visibility = View.GONE
        var status9 = false
        binding.flower9.setOnClickListener {
            if (status9){
                status9 = false
                binding.bookmark9.visibility = View.GONE
            }
            else{
                status9 = true
                binding.bookmark9.visibility = View.VISIBLE
            }
        }

        var changeNum = 0
        binding.sliderWeather.addOnSliderTouchListener(object : OnSliderTouchListener{
            override fun onStartTrackingTouch(slider: Slider) {

            }

            override fun onStopTrackingTouch(slider: Slider) {
                when(changeNum){
                    0 -> {
                        binding.flower.setImageResource(R.drawable.flower_combined)
                        binding.flowerName.text = "안개꽃+장미"

                    }
                    1 -> {
                        binding.flower.setImageResource(R.drawable.blue_tulip)
                        binding.flowerName.text = "튤립"
                    }
                    2 -> {
                        binding.flower.setImageResource(R.drawable.red_rose)
                        binding.flowerName.text = "장미"
                    }
                    3 -> {
                        binding.flower.setImageResource(R.drawable.yellow_fog)
                        binding.flowerName.text = "안개꽃"
                    }
                }
                if (changeNum < 3){
                    changeNum++
                } else{
                    changeNum = 0
                }
            }

        })
        binding.sliderFeeling.addOnSliderTouchListener(object : OnSliderTouchListener{
            override fun onStartTrackingTouch(slider: Slider) {

            }

            override fun onStopTrackingTouch(slider: Slider) {
                when(changeNum){
                    0 -> {
                        binding.flower.setImageResource(R.drawable.flower_combined)
                        binding.flowerName.text = "안개꽃+장미"

                    }
                    1 -> {
                        binding.flower.setImageResource(R.drawable.blue_tulip)
                        binding.flowerName.text = "튤립"
                    }
                    2 -> {
                        binding.flower.setImageResource(R.drawable.red_rose)
                        binding.flowerName.text = "장미"
                    }
                    3 -> {
                        binding.flower.setImageResource(R.drawable.yellow_fog)
                        binding.flowerName.text = "안개꽃"
                    }
                }
                if (changeNum < 3){
                    changeNum++
                } else{
                    changeNum = 0
                }
            }

        })
        binding.sliderlocation.addOnSliderTouchListener(object : OnSliderTouchListener{
            override fun onStartTrackingTouch(slider: Slider) {

            }

            override fun onStopTrackingTouch(slider: Slider) {
                when(changeNum){
                    0 -> {
                        binding.flower.setImageResource(R.drawable.flower_combined)
                        binding.flowerName.text = "안개꽃+장미"

                    }
                    1 -> {
                        binding.flower.setImageResource(R.drawable.blue_tulip)
                        binding.flowerName.text = "튤립"
                    }
                    2 -> {
                        binding.flower.setImageResource(R.drawable.red_rose)
                        binding.flowerName.text = "장미"
                    }
                    3 -> {
                        binding.flower.setImageResource(R.drawable.yellow_fog)
                        binding.flowerName.text = "안개꽃"
                    }
                }
                if (changeNum < 3){
                    changeNum++
                } else{
                    changeNum = 0
                }
            }

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainPageBinding.inflate(layoutInflater)
        return binding.root
    }
}