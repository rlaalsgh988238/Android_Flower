package com.example.tuesday.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tuesday.Data.FlowerData
import com.example.tuesday.Data.SavedData
import com.example.tuesday.Data.favorData
import com.example.tuesday.Object.SavedHandler
import com.example.tuesday.R
import com.example.tuesday.adapter.FlowerAdapter
import com.example.tuesday.adapter.SavedAdapter
import com.example.tuesday.adapter.SelectFavorAdapter
import com.example.tuesday.databinding.ActivityReselectBinding
import com.example.tuesday.databinding.ActivitySavedBinding
import com.example.tuesday.fragment.UserEventFragment

class SavedActivity : AppCompatActivity() {
    lateinit var binding: ActivitySavedBinding

    private var savedlist: ArrayList<SavedData> = arrayListOf()
    private var savedAdapter: SavedAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySavedBinding.inflate(layoutInflater)

        binding.backIv.setOnClickListener {
            finish()
        }
        binding.backTv.setOnClickListener {
            finish()
        }

        if (SavedHandler.savedlist.isEmpty()){

        }else{
            savedlist=SavedHandler.savedlist
            savedAdapter= SavedAdapter(this,savedlist)
            binding.savedRv.adapter=savedAdapter

            val intent = Intent(this, PurchaseActivity::class.java)
            binding.savedRv.layoutManager= GridLayoutManager(this,2)
            binding.savedRv.addItemDecoration(UserEventFragment.SpaceItemDecoration(24))
            savedAdapter!!.setOnItemClickListener(object : SavedAdapter.OnItemClickListener {
                override fun onItemClick() {
                    startActivity(intent)
                }
            }
            )

        }

        setContentView(binding.root)


    }
}