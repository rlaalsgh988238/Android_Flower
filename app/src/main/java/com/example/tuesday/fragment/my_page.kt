package com.example.tuesday.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.tuesday.MainActivity
import com.example.tuesday.activity.PrivacyActivity
import com.example.tuesday.activity.ReselectActivity
import com.example.tuesday.activity.SavedActivity
import com.example.tuesday.activity.SelectFavorActivity
import com.example.tuesday.activity.login_page
import com.example.tuesday.databinding.FragmentMyPageBinding


class my_page : Fragment() {
    lateinit var binding: FragmentMyPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentMyPageBinding.inflate(layoutInflater)
        val name = arguments?.getString("name")
        val email = arguments?.getString("email")

        binding.userName.text = name
        binding.userEmailTv.text=email

        //계약사항 보여주는 곳
        binding.layoutPrivacy.setOnClickListener {
            val intent =
                Intent(requireContext(), PrivacyActivity::class.java) //fragment라서 activity intent와는 다른 방식
            startActivity(intent)
        }
        //logout
        binding.layoutLogout.setOnClickListener {
            if (requireActivity() is MainActivity) {
                (requireActivity() as MainActivity).closeFragment()
            }
            val intent =
                Intent(requireContext(), login_page::class.java) //fragment라서 activity intent와는 다른 방식
            startActivity(intent)

        }
        //취향 재선택
        binding.layoutReselect.setOnClickListener {
            val intent =
                Intent(requireContext(), ReselectActivity::class.java) //fragment라서 activity intent와는 다른 방식
            startActivity(intent)

        }
        //저장됨
        binding.layoutSaved.setOnClickListener {
            val intent =
                Intent(requireContext(), SavedActivity::class.java) //fragment라서 activity intent와는 다른 방식
            startActivity(intent)
        }

        return binding.root


    }


}