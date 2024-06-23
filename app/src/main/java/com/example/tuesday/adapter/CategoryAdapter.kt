package com.example.tuesday.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tuesday.databinding.ItemCategoryBinding
import com.example.tuesday.databinding.ItemEventBinding
import com.example.tuesday.fragment.CategoryData
import com.example.tuesday.fragment.EventData

class CategoryAdapter(val context: Context, val items: ArrayList<CategoryData>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CategoryData){

            binding.categoryIv.setImageResource(item.img)
            }
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ViewHolder {
            val binding =
                ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}