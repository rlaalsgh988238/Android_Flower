package com.example.tuesday.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tuesday.Data.FlowerData
import com.example.tuesday.Data.SavedData
import com.example.tuesday.Data.favorData
import com.example.tuesday.Object.SavedHandler
import com.example.tuesday.R
import com.example.tuesday.databinding.ItemFavorBinding
import com.example.tuesday.databinding.ItemFlowerPictureBinding

class SavedAdapter(val context: Context, val items: ArrayList<SavedData>) :
    RecyclerView.Adapter<SavedAdapter.ViewHolder>() {

    private lateinit var itemClickListener: OnItemClickListener
    interface OnItemClickListener {
        fun onItemClick()
    }
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        itemClickListener = onItemClickListener
    }
    inner class ViewHolder(val binding: ItemFlowerPictureBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SavedData) {

            Log.d("namename",item.flowerName)

            binding.flowerNameTv.text = item.flowerName
            binding.priceTv.text=item.price
            binding.flowerPicIv.setImageResource(item.flowerImg)
            binding.likeIv.setImageResource(R.drawable.like_on_small)

            binding.likeIv.setOnClickListener {

                binding.likeIv.setImageResource(R.drawable.liked_off)
                item.like = false
                SavedHandler.savedlist.removeIf { savedData ->
                    savedData.flowerName == item.flowerName
                }
                notifyDataSetChanged()

            }
            binding.flowerPicIv.setOnClickListener {
                itemClickListener.onItemClick()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedAdapter.ViewHolder {
        val binding =
            ItemFlowerPictureBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SavedAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}