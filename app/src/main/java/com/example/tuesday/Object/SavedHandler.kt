package com.example.tuesday.Object

import com.example.tuesday.AI.ChatGPT
import com.example.tuesday.AI.RecommendFlowerData
import com.example.tuesday.Data.EventData
import com.example.tuesday.Data.FlowerData
import com.example.tuesday.Data.SavedData
import kotlin.random.Random

object SavedHandler {

    var savedlist: ArrayList<SavedData> = arrayListOf()
    fun addSavedList(flowerData: RecommendFlowerData){
        savedlist.add(makeSavedList(flowerData))
    }

    fun deleteSavedList(savedData: SavedData){
        savedlist.remove(savedData)
    }

    fun makeSavedList(flowerData: RecommendFlowerData): SavedData{
        var chatGPT = ChatGPT()
        var saveData = SavedData(
            flowerData.flower.name,
            chatGPT.getFlowerImageSmaller(flowerData),
            getPrice(flowerData.flower.name),
            true
        )
        return saveData
    }

    fun getPrice(flowerName: String): String{
        var price = (1..3).random()

        when(flowerName){
            "장미" -> price = 62000
            "백합" -> price = 88000
            "카네이션" -> price = 62000
            else -> price = 60000+price*10000
        }
        val result = price.toString() + "원"

        return result
    }
}
