package com.example.tuesday.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.os.persistableBundleOf
import androidx.recyclerview.widget.RecyclerView
import com.example.tuesday.R
import com.example.tuesday.R.color.light_gray
import com.example.tuesday.calendar.ScheduleModel
import com.example.tuesday.calendar.UtilObject
import com.example.tuesday.databinding.ScheduleItemBinding
import kotlinx.coroutines.NonDisposableHandle.parent

class ScheduleListAdapter(val context: Context): RecyclerView.Adapter<ScheduleListAdapter.ViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder = ViewHolder(ScheduleItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setUi()

        holder.binding.schedule.setOnClickListener {
            UtilObject.listeningActivity.scheduleClicked(position)
        }
    }
    override fun getItemCount(): Int = ScheduleModel.schedule.size

    inner class ViewHolder(itemView: ScheduleItemBinding): RecyclerView.ViewHolder(itemView.root){
        val binding = itemView
        val schedule = binding.schedule


        @SuppressLint("ResourceAsColor", "SetTextI18n")
        fun setUi(){
            schedule.text = "D-${ScheduleModel.schedule[adapterPosition].D_day} ${ScheduleModel.schedule[adapterPosition].scheduleName}"
            if (adapterPosition != 0){
                schedule.setTextColor(ContextCompat.getColor(context, R.color.light_gray))
            }
        }
    }
}