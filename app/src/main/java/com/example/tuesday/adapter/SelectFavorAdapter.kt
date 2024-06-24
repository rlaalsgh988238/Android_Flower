package com.example.tuesday.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tuesday.Data.CategoryData
import com.example.tuesday.Data.favorData
import com.example.tuesday.R
import com.example.tuesday.databinding.ItemCategoryBinding
import com.example.tuesday.databinding.ItemFavorBinding
import com.example.tuesday.databinding.ItemFlowerPictureBinding

class SelectFavorAdapter(val context: Context, val items: ArrayList<favorData>) :
    RecyclerView.Adapter<SelectFavorAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemFavorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: favorData){
            var like=false

            binding.flowerNameTv.text=item.flowerName
            binding.flowerPicIv.setImageResource(item.flowerImg)

            binding.cl.setOnClickListener {
                if(like){
                    binding.likeIv.setImageResource(R.drawable.liked_off)
                    like=false
                }else{
                    binding.likeIv.setImageResource(R.drawable.like_on_small)
                    like=true
                }
            }

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectFavorAdapter.ViewHolder {
        val binding =
            ItemFavorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SelectFavorAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}