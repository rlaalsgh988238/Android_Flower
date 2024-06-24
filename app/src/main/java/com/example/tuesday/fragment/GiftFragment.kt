package com.example.tuesday.fragment

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tuesday.Data.CategoryData
import com.example.tuesday.Data.FlowerData
import com.example.tuesday.Object.EventDataHolder
import com.example.tuesday.R
import com.example.tuesday.activity.BuyConfirmedActivity
import com.example.tuesday.activity.PurchaseActivity
import com.example.tuesday.adapter.CategoryAdapter
import com.example.tuesday.adapter.FlowerAdapter
import com.example.tuesday.adapter.UserEventAdapter
import com.example.tuesday.databinding.FragmentGiftBinding


class GiftFragment : Fragment() {
    private var allBtn: Boolean = true
    private var giftBtn: Boolean = false
    private var birthBtn: Boolean = false
    private var eventBtn: Boolean = false


    val green = ColorStateList.valueOf(Color.parseColor("#475E3E"))
    val gray = ColorStateList.valueOf(Color.parseColor("#D0D5DD"))

    private var flowerlist: ArrayList<FlowerData> = arrayListOf()
    private var flowerlistevent: ArrayList<FlowerData> = arrayListOf()
    private var flowerlistbirth: ArrayList<FlowerData> = arrayListOf()
    private var flowerlistgift: ArrayList<FlowerData> = arrayListOf()
    private var flowerAdapter: FlowerAdapter? = null
    private lateinit var binding: FragmentGiftBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGiftBinding.inflate(layoutInflater)
        val name = arguments?.getString("name")
        binding.userTv.text = name+"님"

        initData()
        initRecyclerView()
        btnColorChange()

        return binding.root
    }

    private fun initRecyclerView() {

        val intent = Intent(requireContext(), PurchaseActivity::class.java)
        flowerAdapter = FlowerAdapter(requireContext(), flowerlist)
        binding.flowerRv.adapter = flowerAdapter
        binding.flowerRv.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.flowerRv.addItemDecoration(UserEventFragment.SpaceItemDecoration(24))
        flowerAdapter!!.setOnItemClickListener(object : FlowerAdapter.OnItemClickListener {
            override fun onItemClick() {
                startActivity(intent)
            }
        }
        )

    }

    private fun initData() {
        //1->행사
        //2->생일
        //3->선물
        flowerlist.addAll(
            arrayListOf(
                FlowerData("카네이션", R.drawable.fff1, "62,000원", false, 1),
                FlowerData("프리지아", R.drawable.fff2, "67,000원", false, 2),
                FlowerData("백합", R.drawable.fff3, "88,000원", false, 2),
                FlowerData("장미", R.drawable.fff4, "62,000원", false, 3),
                FlowerData("수국", R.drawable.fff5, "68,000원", false, 3),
                FlowerData("안시리움", R.drawable.fff6, "60,000원", false, 2),
                FlowerData("몬스테라", R.drawable.fff7, "72,000원", false, 2),
                FlowerData("선인장", R.drawable.fff8, "42,000원", false, 1),
                FlowerData("돈나무", R.drawable.fff9, "99,000원", false, 3),
                FlowerData("홍콩야자", R.drawable.fff10, "52,000원", false, 1),
            )
        )


        flowerlistevent.addAll(
            arrayListOf(
                FlowerData("카네이션", R.drawable.fff1, "62,000원", false, 1),
                FlowerData("선인장", R.drawable.fff8, "42,000원", false, 1),
                FlowerData("홍콩야자", R.drawable.fff10, "52,000원", false, 1),
            )
        )
        flowerlistbirth.addAll(
            arrayListOf(
                FlowerData("프리지아", R.drawable.fff2, "67,000원", false, 2),
                FlowerData("백합", R.drawable.fff3, "88,000원", false, 2),
                FlowerData("안시리움", R.drawable.fff6, "60,000원", false, 2),
                FlowerData("몬스테라", R.drawable.fff7, "72,000원", false, 2),
            )
        )

        flowerlistgift.addAll(
            arrayListOf(
                FlowerData("장미", R.drawable.fff4, "62,000원", false, 3),
                FlowerData("수국", R.drawable.fff5, "68,000원", false, 3),
                FlowerData("돈나무", R.drawable.fff9, "99,000원", false, 3),
            )
        )
    }

    private fun setBtncolor() {
        //today

        if (allBtn) {
            //참이면 이렇게 거짓이면 다르게
            binding.allBtn.setBackgroundColor(Color.parseColor("#475E3E"))
            binding.allTv.setTextColor((Color.parseColor("#FFFFFF")))
            binding.allBtn.strokeColor = green
        } else {
            binding.allBtn.setBackgroundColor(Color.parseColor("#FFFFFF"))
            binding.allTv.setTextColor((Color.parseColor("#D0D5DD")))
            binding.allBtn.strokeColor = gray
        }

        //week
        if (giftBtn) {
            //참이면 이렇게 거짓이면 다르게
            binding.giftBtn.setBackgroundColor(Color.parseColor("#475E3E"))
            binding.giftTtv.setTextColor((Color.parseColor("#FFFFFF")))
            binding.giftBtn.strokeColor = green

        } else {
            binding.giftBtn.setBackgroundColor(Color.parseColor("#FFFFFF"))
            binding.giftTtv.setTextColor((Color.parseColor("#D0D5DD")))
            binding.giftBtn.strokeColor = gray
        }

        //month
        if (birthBtn) {
            //참이면 이렇게 거짓이면 다르게
            binding.birthBtn.setBackgroundColor(Color.parseColor("#475E3E"))
            binding.birthTv.setTextColor((Color.parseColor("#FFFFFF")))
            binding.birthBtn.strokeColor = green
        } else {
            binding.birthBtn.setBackgroundColor(Color.parseColor("#FFFFFF"))
            binding.birthTv.setTextColor((Color.parseColor("#D0D5DD")))
            binding.birthBtn.strokeColor = gray
        }
        //month

        if (eventBtn) {
            //참이면 이렇게 거짓이면 다르게
            binding.eventBtn.setBackgroundColor(Color.parseColor("#475E3E"))
            binding.eventTtv.setTextColor((Color.parseColor("#FFFFFF")))
            binding.eventBtn.strokeColor = green
        } else {
            binding.eventBtn.setBackgroundColor(Color.parseColor("#FFFFFF"))
            binding.eventTtv.setTextColor((Color.parseColor("#D0D5DD")))
            binding.eventBtn.strokeColor = gray
        }
    }

    private fun btnColorChange() {

        binding.allBtn.setOnClickListener {

            flowerAdapter?.updateFlowerList(flowerlist)
            if (!allBtn) {
                allBtn = true
                giftBtn = false
                birthBtn = false
                eventBtn = false
                setBtncolor()

            }
        }
        binding.giftBtn.setOnClickListener {

            flowerAdapter?.updateFlowerList(flowerlistgift)
            if (!giftBtn) {
                allBtn = false
                giftBtn = true
                birthBtn = false
                eventBtn = false
                setBtncolor()

            }
        }
        binding.birthBtn.setOnClickListener {

            flowerAdapter?.updateFlowerList(flowerlistbirth)
            if (!birthBtn) {
                allBtn = false
                giftBtn = false
                birthBtn = true
                eventBtn = false
                setBtncolor()
            }
        }
        binding.eventBtn.setOnClickListener {

            flowerAdapter?.updateFlowerList(flowerlistevent)
            if (!eventBtn) {
                allBtn = false
                giftBtn = false
                birthBtn = false
                eventBtn = true
                setBtncolor()
            }
        }

    }

    override fun onStart() {
        super.onStart()
        if (EventDataHolder.itemList.isEmpty()) {
            // 비어 있으면 그냥 넘어감
            Log.d("initData", "itemList is empty")
        } else {
            // 비어 있지 않으면 첫 번째 아이템 출력
            Log.d("initData", EventDataHolder.itemList[0].toString())
        }
    }

    override fun onResume() {
        super.onResume()
        if (EventDataHolder.itemList.isEmpty()) {
            // 비어 있으면 그냥 넘어감
            Log.d("initData", "itemList is empty")
        } else {
            // 비어 있지 않으면 첫 번째 아이템 출력
            Log.d("initData", EventDataHolder.itemList[0].eventName)
            binding.eventTv.text = EventDataHolder.itemList[0].eventName
        }
    }

}