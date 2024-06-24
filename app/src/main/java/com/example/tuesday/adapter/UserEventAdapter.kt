package com.example.tuesday.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tuesday.databinding.ItemEventBinding
import com.example.tuesday.Data.EventData

class UserEventAdapter(val context: Context, val items: ArrayList<EventData>, var btnState:String, var currentMonth:Int, var currentDay:Int) :
    RecyclerView.Adapter<UserEventAdapter.ViewHolder>() {

    private lateinit var itemClickListener: OnItemClickListener
    fun setState(newState: String) {
        btnState = newState
        //여기서 버튼 상태 받아와서 재정렬
        notifyDataSetChanged()
    }
    fun setDate(month:Int,day:Int) {
        currentMonth=month
        currentDay=day
        //날짜 재선택
        notifyDataSetChanged()
    }
    inner class ViewHolder(val binding: ItemEventBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: EventData) {
            Log.d("btnstateM",currentMonth.toString())
            Log.d("btnstateD",currentDay.toString())
            Log.d("btnstateB",btnState.toString())

            val start = item.eventStart.substring(0..9) // 0부터 9까지의 범위를 추출
            //"예시로 2024-06-26"으로 나옴
            var month=start.substring(5..6)
            var day=start.substring(8..9)
            var eventMonth = when (month) {
                "01" -> "Jan"
                "02" -> "Feb"
                "03" -> "Mar"
                "04" -> "Apr"
                "05" -> "May"
                "06" -> "Jun"
                "07" -> "Jul"
                "08" -> "Aug"
                "09" -> "Sep"
                "10" -> "Oct"
                "11" -> "Nov"
                "12" -> "Dec"
                else -> throw IllegalArgumentException("Invalid month format")
            }
            //TODO:만약 day가 더 크면 bind 스킵해서 안 보여주게..?& 버튼선택된 날짜 및 캘린더의 날짜가 필요
            var date= "$eventMonth, $day"
            //TODO: 현재 버튼 State에 따라 다르게 검토하게 , 그 후 현재 선택된 month,day 비교
            if(btnState=="today"){
                //여기서 각각 currentdate와 비교해서 언제까지 가능한지
                //우선 month랑 day가 같은경우만
                if (day.toInt() ==currentDay && month.toInt()==currentMonth) {
                    val date = "$eventMonth, $day"
                    binding.root.visibility = View.VISIBLE // 보이게 설정
                    binding.dateTv.text = date
                    binding.eventTv.text = item.eventName
                    binding.layoutCl.setOnClickListener{
                        itemClickListener.onItemClick(item.eventName)

                    }

                } else {
                    binding.root.visibility = View.GONE
                }

            }else if(btnState=="week"){
                //주 단위인데, 주도 달은 같아야 하나...?
                //TODO:예외 케이스로 31일인데 다음주 1일에 일정 있는 경우
                if ((day.toInt() in currentDay..(currentDay + 7)) && month.toInt()==currentMonth) {
                    val date = "$eventMonth, $day"
                    binding.root.visibility = View.VISIBLE // 보이게 설정
                    binding.dateTv.text = date
                    binding.eventTv.text = item.eventName

                    binding.layoutCl.setOnClickListener{
                        itemClickListener.onItemClick(item.eventName)

                    }

                } else if ((day.toInt() in currentDay-30..(currentDay + 7-30)) && month.toInt()==currentMonth+1) {
                    // 주 단위
                    val date = "$eventMonth, $day"
                    binding.root.visibility = View.VISIBLE // 보이게 설정
                    binding.dateTv.text = date
                    binding.eventTv.text = item.eventName

                    binding.layoutCl.setOnClickListener{
                        itemClickListener.onItemClick(item.eventName)

                    }

                }else{
                    binding.root.visibility = View.GONE
                }
            }else if(btnState=="month"){

                if ((day.toInt() in currentDay..(31)) && month.toInt()==currentMonth) {
                    val date = "$eventMonth, $day"
                    binding.root.visibility = View.VISIBLE // 보이게 설정
                    binding.dateTv.text = date
                    binding.eventTv.text = item.eventName

                    binding.layoutCl.setOnClickListener{
                        itemClickListener.onItemClick(item.eventName)

                    }

                } else if ((day.toInt() <=currentDay) && month.toInt()==currentMonth+1) {
                    // 주 단위
                    val date = "$eventMonth, $day"
                    binding.root.visibility = View.VISIBLE // 보이게 설정
                    binding.dateTv.text = date
                    binding.eventTv.text = item.eventName

                    binding.layoutCl.setOnClickListener{
                        itemClickListener.onItemClick(item.eventName)
                    }

                }else{
                    binding.root.visibility = View.GONE
                }
            }else(
                    //err
                Log.d("err","잘못된 버튼 입력방식")
            )
        }

    }
    interface OnItemClickListener {
        fun onItemClick(eventName: String)
    }
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        itemClickListener = onItemClickListener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserEventAdapter.ViewHolder {
        val binding =
            ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: UserEventAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size

    }

}