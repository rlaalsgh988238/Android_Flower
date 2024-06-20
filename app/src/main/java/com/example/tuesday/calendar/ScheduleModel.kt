package com.example.tuesday.calendar

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.tuesday.R

object ScheduleModel {
    init {
        Log.d("ScheduleModel","Init")
        addSchedule()
    }
    lateinit var schedule: ArrayList<Schedule>

    private fun addSchedule(){
        schedule = ArrayList<Schedule>()
        schedule.add(FirstSchedule())
        schedule.add(SecondSchedule())
        schedule.add(ThirdSchedule())
        schedule.add(FourthSchedule())
        schedule.add(ETCSchedule())
        schedule.add(ETCSchedule())
        schedule.add(ETCSchedule())
    }
}

interface Schedule{
    var D_day: Int
    var scheduleName: String
    var flower: ArrayList<FlowerInterface>
}

data class FirstSchedule(
    override var D_day: Int = 3,
    override var scheduleName: String = "결혼 기념일",
    override var flower:ArrayList<FlowerInterface> = ArrayList<FlowerInterface>()
): Schedule{
    init {
        flower.add(RedRose())
    }
}

data class SecondSchedule(
    override var D_day: Int = 12,
    override var scheduleName: String = "HCI 마지막",
    override var flower:ArrayList<FlowerInterface> = ArrayList<FlowerInterface>()
): Schedule{
    init {
        flower.add(BlueTulip())
    }
}
data class ThirdSchedule(
    override var D_day: Int = 30,
    override var scheduleName: String = "부모님 결혼기념일",
    override var flower:ArrayList<FlowerInterface> = ArrayList<FlowerInterface>()
): Schedule{
    init {
        flower.add(YellowFog())
    }
}

data class FourthSchedule(
    override var D_day: Int = 55,
    override var scheduleName: String = "남친이랑 1주년",
    override var flower:ArrayList<FlowerInterface> = ArrayList<FlowerInterface>()
): Schedule{
    init {
        flower.add(RedRose())
    }
}

data class ETCSchedule(
    override var D_day: Int = 100,
    override var scheduleName: String = "기타",
    override var flower:ArrayList<FlowerInterface> = ArrayList<FlowerInterface>()
): Schedule{
    init {
        flower.add(BlueTulip())
    }
}




interface FlowerInterface{
    val flowerImage: Int
    val flowerName: String
    val flowerColor: Int
    val flowerMeaning: String
    val price: Int
    val notice: String
}

data class RedRose(
    override val flowerImage: Int = R.drawable.red_rose,
    override val flowerName: String = "빨간 장미",
    override val flowerColor: Int = R.color.red,
    override val flowerMeaning: String = "사랑, 아름다움, 낭만적인 사랑",
    override val price: Int = 3000,
    override val notice: String = "가시 주의"
) : FlowerInterface

data class BlueTulip(
    override val flowerImage: Int = R.drawable.blue_tulip,
    override val flowerName: String = "파란 튤립",
    override val flowerColor: Int = R.color.blue,
    override val flowerMeaning: String = "사랑의 맹세",
    override val price: Int = 3500,
    override val notice: String = "알칼로이드 주의"
) : FlowerInterface

data class YellowFog(
    override val flowerImage: Int = R.drawable.yellow_fog,
    override val flowerName: String = "노란 안개꽃",
    override val flowerColor: Int = R.color.yellow,
    override val flowerMeaning: String = "성공, 성취, 기쁨",
    override val price: Int = 3500,
    override val notice: String = "알칼로이드 주의"
) : FlowerInterface