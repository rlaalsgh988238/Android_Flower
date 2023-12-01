package com.example.tuesday.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.persistableBundleOf
import androidx.recyclerview.widget.RecyclerView
import com.example.tuesday.R
import com.example.tuesday.calendar.BlueTulip
import com.example.tuesday.calendar.FlowerInterface
import com.example.tuesday.calendar.RedRose
import com.example.tuesday.calendar.UtilObject
import com.example.tuesday.calendar.YellowFog
import com.example.tuesday.databinding.FlowerPhotoItemBinding
import com.example.tuesday.databinding.ScheduleItemBinding

class FlowerListRecyclerAdapter: RecyclerView.Adapter<FlowerListRecyclerAdapter.ViewHolder>() {
    var scheduleNum = 0

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FlowerListRecyclerAdapter.ViewHolder = ViewHolder(FlowerPhotoItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setUi()
        val redRoseNum = 0
        val blueTulipNum = 1
        val yellowFogNum = 2

        holder.binding.layout.setOnClickListener {
            when(position){
                redRoseNum -> {

                }
                blueTulipNum ->{

                }
                yellowFogNum -> {

                }
            }
        }

    }

    override fun getItemCount(): Int = 3

    fun setSchedulePosition(position: Int){
        scheduleNum = position
    }

    class ViewHolder(itemView: FlowerPhotoItemBinding): RecyclerView.ViewHolder(itemView.root){
        val binding = itemView
        val flowerImage = binding.flowerImage
        lateinit var flower: FlowerInterface
        fun setUi(){
            when(adapterPosition){
                0 -> flower = RedRose()
                1 -> flower = BlueTulip()
                2 -> flower = YellowFog()
                else -> flower = YellowFog()
            }
            flowerImage.setImageResource(flower.flowerImage)
        }
        fun checkFlowerList(){

        }

        fun checkClicked(): Boolean{

            return true
        }

        fun addFlower(){

        }

        fun deleteFlower(){

        }
    }
}