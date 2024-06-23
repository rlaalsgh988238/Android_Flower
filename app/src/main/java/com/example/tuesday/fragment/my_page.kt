package com.example.tuesday.fragment

import android.content.Intent.getIntent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tuesday.R
import com.example.tuesday.databinding.FragmentCalendarBinding
import com.example.tuesday.databinding.FragmentMainPageBinding
import com.example.tuesday.databinding.FragmentMyPageBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [my_page.newInstance] factory method to
 * create an instance of this fragment.
 */
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


        return binding.root


    }


}