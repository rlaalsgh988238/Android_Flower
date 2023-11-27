package com.example.tuesday.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tuesday.R
import com.example.tuesday.adapter.ScheduleListAdapter
import com.example.tuesday.calendar.UtilListener
import com.example.tuesday.databinding.FragmentScheduleBinding

class ScheduleFragment : Fragment() {
    lateinit var binding: FragmentScheduleBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUi()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScheduleBinding.inflate(layoutInflater)

        return binding.root
    }

    private fun setUi(){
        binding.recyclerView.adapter = ScheduleListAdapter(requireContext())
    }


}