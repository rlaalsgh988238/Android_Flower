package com.example.tuesday.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tuesday.Data.FlowerData
import com.example.tuesday.Data.favorData
import com.example.tuesday.MainActivity
import com.example.tuesday.R
import com.example.tuesday.adapter.FlowerAdapter
import com.example.tuesday.adapter.SelectFavorAdapter
import com.example.tuesday.databinding.ActivityPushBinding
import com.example.tuesday.databinding.ActivitySelectFavorBinding
import com.example.tuesday.fragment.UserEventFragment
import kotlinx.coroutines.MainScope

class SelectFavorActivity : AppCompatActivity() {
    lateinit var binding: ActivitySelectFavorBinding

    private var favorlist:ArrayList<favorData> = arrayListOf()
    private var favorAdapter: SelectFavorAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySelectFavorBinding.inflate(layoutInflater)



        favorlist.addAll(arrayListOf(
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
            ))


        favorAdapter= SelectFavorAdapter(this,favorlist)
        binding.favorRv.adapter=favorAdapter
        binding.favorRv.layoutManager= GridLayoutManager(this,2)
        binding.favorRv.addItemDecoration(UserEventFragment.SpaceItemDecoration(24))
        val email = intent.getStringExtra("email")
        // ViewPager 설정 및 초기화
        val name = intent.getStringExtra("name") // 사용자 이름 받아오기


        binding.keepIv.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)//예시로 해놓은것이니 고쳐야함
            //어디로 보낼지 저ㄱ는코드
            //이메일을 보내고 있는데 사용자 이름 받아서 보내는것도 하자
            intent.putExtra("email", email)
            intent.putExtra("name", name)
            finish()
            startActivity(intent)

        }

        setContentView(binding.root)
    }
}