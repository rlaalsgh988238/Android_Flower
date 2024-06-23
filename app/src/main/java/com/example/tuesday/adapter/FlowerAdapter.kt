package com.example.tuesday.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tuesday.Data.CategoryData
import com.example.tuesday.Data.FlowerData
import com.example.tuesday.databinding.ItemCategoryBinding
import com.example.tuesday.databinding.ItemFlowerPictureBinding


class FlowerAdapter(val context: Context, val items: ArrayList<FlowerData>) :
    RecyclerView.Adapter<FlowerAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemFlowerPictureBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FlowerData){

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlowerAdapter.ViewHolder {
        val binding =
            ItemFlowerPictureBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FlowerAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}