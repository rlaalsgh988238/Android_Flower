package com.example.tuesday.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tuesday.Data.CategoryData
import com.example.tuesday.Data.FlowerData
import com.example.tuesday.Data.SavedData
import com.example.tuesday.Object.SavedHandler
import com.example.tuesday.R
import com.example.tuesday.databinding.ItemCategoryBinding
import com.example.tuesday.databinding.ItemFlowerPictureBinding


class FlowerAdapter(val context: Context, private var flowerList: ArrayList<FlowerData>) :
    RecyclerView.Adapter<FlowerAdapter.ViewHolder>() {


    private lateinit var itemClickListener: OnItemClickListener
    interface OnItemClickListener {
        fun onItemClick()
    }
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        itemClickListener = onItemClickListener
    }
    inner class ViewHolder(val binding: ItemFlowerPictureBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FlowerData) {
            var like=false
            binding.flowerPicIv.setImageResource(item.flowerImg)
            binding.flowerNameTv.text=item.flowerName
            binding.priceTv.text=item.price
            if(item.like){
                binding.likeIv.setImageResource(R.drawable.like_on_small)
            }else{
                binding.likeIv.setImageResource(R.drawable.liked_off)
            }
            binding.likeIv.setOnClickListener {
                if (like) {
                    binding.likeIv.setImageResource(R.drawable.liked_off)
                    like = false
                    item.like=false
                    SavedHandler.savedlist.removeIf { savedData ->
                        savedData.flowerName == item.flowerName
                    }

                } else {
                    binding.likeIv.setImageResource(R.drawable.like_on_small)

                    val filteredList = SavedHandler.savedlist.filter { savedData ->
                        savedData.flowerName == item.flowerName
                    }

                    if (filteredList.isNotEmpty()) {

                    } else {
                        SavedHandler.savedlist.add(SavedData(item.flowerName,item.flowerImg,item.price,item.like))
                    }
                    item.like=true
                    like = true
                    //저장됨 리스트에 아이템 추가하기 ㅋㅋ
                }
            }
            binding.flowerPicIv.setOnClickListener {
                itemClickListener.onItemClick()
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlowerAdapter.ViewHolder {
        val binding =
            ItemFlowerPictureBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FlowerAdapter.ViewHolder, position: Int) {
        holder.bind(flowerList[position])
    }

    override fun getItemCount(): Int {
        return flowerList.size
    }

    fun updateFlowerList(newList: ArrayList<FlowerData>) {
        flowerList = newList
        notifyDataSetChanged()
    }
}