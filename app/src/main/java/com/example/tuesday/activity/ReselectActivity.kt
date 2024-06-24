package com.example.tuesday.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tuesday.Data.favorData
import com.example.tuesday.MainActivity
import com.example.tuesday.R
import com.example.tuesday.adapter.SelectFavorAdapter
import com.example.tuesday.databinding.ActivityReselectBinding
import com.example.tuesday.databinding.ActivitySelectFavorBinding
import com.example.tuesday.fragment.UserEventFragment

class ReselectActivity : AppCompatActivity() {
    lateinit var binding: ActivityReselectBinding

    private var favorlist: ArrayList<favorData> = arrayListOf()
    private var favorAdapter: SelectFavorAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReselectBinding.inflate(layoutInflater)



        favorlist.addAll(
            arrayListOf(
                favorData("카네이션",R.drawable.fff1),
                favorData("프리지아",R.drawable.fff2),
                favorData("백합",R.drawable.fff3),
                favorData("장미",R.drawable.fff4),
                favorData("수국",R.drawable.fff5),
                favorData("안시리움",R.drawable.fff6),
                favorData("몬스테라",R.drawable.fff7),
                favorData("선인장",R.drawable.fff8,),
                favorData("돈나무",R.drawable.fff9),
                favorData("홍콩야자",R.drawable.fff10),
            )
        )


        favorAdapter = SelectFavorAdapter(this, favorlist)
        binding.favorRv.adapter = favorAdapter
        binding.favorRv.layoutManager = GridLayoutManager(this, 2)
        binding.favorRv.addItemDecoration(UserEventFragment.SpaceItemDecoration(24))
        val email = intent.getStringExtra("email")
        // ViewPager 설정 및 초기화
        val name = intent.getStringExtra("name") // 사용자 이름 받아오기


        binding.keepIv.setOnClickListener {
            finish()
        }

        setContentView(binding.root)
    }

}