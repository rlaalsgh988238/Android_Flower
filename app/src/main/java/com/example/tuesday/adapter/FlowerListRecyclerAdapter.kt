package com.example.tuesday.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.persistableBundleOf
import androidx.recyclerview.widget.RecyclerView
import com.example.tuesday.R
import com.example.tuesday.calendar.BlueTulip
import com.example.tuesday.calendar.FlowerInterface
import com.example.tuesday.calendar.RedRose
import com.example.tuesday.calendar.YellowFog
import com.example.tuesday.databinding.FlowerPhotoItemBinding
import com.example.tuesday.databinding.ScheduleItemBinding

class FlowerListRecyclerAdapter: RecyclerView.Adapter<FlowerListRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FlowerListRecyclerAdapter.ViewHolder = ViewHolder(FlowerPhotoItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setUi()
        var status = false
        holder.binding.layout.setOnClickListener {
            holder.binding.layout.setBackgroundResource(
                if(!status){
                    status = true
                    R.drawable.border_line
                }
                else{
                    status = false
                    R.drawable.no_border
                }
            )
        }
    }

    override fun getItemCount(): Int = 3

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
    }
}