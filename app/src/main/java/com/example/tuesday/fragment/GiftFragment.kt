package com.example.tuesday.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tuesday.R
import com.example.tuesday.adapter.CategoryAdapter
import com.example.tuesday.adapter.UserEventAdapter
import com.example.tuesday.databinding.FragmentGiftBinding
import com.example.tuesday.databinding.FragmentUserEventBinding


class GiftFragment : Fragment() {

    private var itemlist:ArrayList<CategoryData> = arrayListOf()
    private var categoryAdapter: CategoryAdapter?=null
    private lateinit var binding: FragmentGiftBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentGiftBinding.inflate(layoutInflater)

        initData()
        initRecyclerView()
        return binding.root
    }

    private fun initRecyclerView() {
        categoryAdapter= CategoryAdapter(requireContext(),itemlist)
        binding.categoryRv.adapter=categoryAdapter
        binding.categoryRv.layoutManager=
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        binding.categoryRv.addItemDecoration(UserEventFragment.SpaceItemDecoration(12))
    }

    private fun initData() {
        itemlist.addAll(arrayListOf(
            CategoryData(R.drawable.all),
            CategoryData(R.drawable.friends_birth_off),
            CategoryData(R.drawable.graduation_off),
            CategoryData(R.drawable.fatherday_off),
            CategoryData(R.drawable.promotion_off),
            CategoryData(R.drawable.girlfriend_off),
            CategoryData(R.drawable.boyfriend_off),
            CategoryData(R.drawable.parent_off),
        ))

    }


}