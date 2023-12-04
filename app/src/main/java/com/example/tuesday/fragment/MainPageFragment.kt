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