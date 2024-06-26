package com.example.tuesday.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tuesday.R
import com.example.tuesday.databinding.FragmentUserCalendarBinding
import com.example.tuesday.databinding.FragmentUserEventBinding

class UserCalendarFragment : Fragment() {
    private lateinit var binding: FragmentUserCalendarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentUserCalendarBinding.inflate(layoutInflater)


        return binding.root
    }

}