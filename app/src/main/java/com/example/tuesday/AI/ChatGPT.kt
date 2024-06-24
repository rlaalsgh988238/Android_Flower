package com.example.tuesday.AI

import android.util.Log
import com.example.tuesday.AI.model.ApiResponse
import com.example.tuesday.AI.model.Flower
import com.example.tuesday.AI.model.Message
import com.example.tuesday.AI.model.RequestBody
import com.example.tuesday.AI.model.ResponseContent
import com.example.tuesday.AI.retrofit.RetrofitClient
import com.example.tuesday.R
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList
import java.util.Objects
import java.util.concurrent.CountDownLatch

class ChatGPT {

    private var messages = listOf(
        Message(role = "system", content = "해당 꽃에 어울리는 문구를 생성하여 응답합니다. 사용자가 입력한 일정 정보를 분석하여 적절한 꽃과 문구를 추천합니다. 입력된 키워드에 따라 사전에 정의된 규칙을 사용하여 꽃을 선택합니다. 이벤트(event)는 사용자로부터 받은 일정 정보로, 사용자가 입력한 정보와 동일합니다. 응답은 다음 형식을 따라야 합니다:\n\n{\n \"flowerEvent\": \"true\",\n \"event\": \"사용자의 일정\",\n \"flower\": {\n \"name\": \"꽃의 이름\",\n \"symbolism\": \"꽃의 꽃말\",\n \"description\": \"이 꽃을 추천한 이유\"\n },\n \"message\": \"일정과 어울리는 문구\"\n}\n\n예를 들어, \"정안 생일\"에 대한 응답은 다음과 같습니다:\n\n{\n \"flowerEvent\": \"true\",\n \"event\": \"정안 생일\",\n \"flower\": {\n \"name\": \"튤립\",\n \"symbolism\": \"사랑과 감사\",\n \"description\": \"튤립은 사랑과 감사의 상징으로 자주 선물되는 꽃이며, 화려한 색상으로 분위기를 밝혀줍니다.\"\n },\n \"message\": \"정안의 행복한 생일을 축하합니다!\"\n}\n\nflowerEvent에 대한 내용은 꽃을 추천할만한 일정인지 판단하는 것 입니다.\n만약 \"점심약속\"과 같이 꽃을 추천할만한 일정이 아니라면, flowerEvent의 값을 false로 합니다.\n그리고 event와 message만 생성하고 flower에 대한 내용은 작성하지 않습니다."),
        Message(role = "user", content = "엄마 생일")
    )

    fun getFlowerData(calendarData: String, callback: (RecommendFlowerData?) -> Unit) {
        // 사용자 메시지를 업데이트
        messages = messages.map { message ->
            if (message.role == "user") {
                message.copy(content = calendarData)
            } else {
                message
            }
        }
        val requestBody = RequestBody(model = "gpt-4o", messages = messages)
        RetrofitClient.api.createRequest(requestBody).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    apiResponse?.let {
                        val rawContent = it.choices[0].message.content
                        val content = Gson().fromJson(rawContent, ResponseContent::class.java)
                        val flowerInfo = content.flower?.let { flower ->
                            FlowerInfo(name = flower.name, symbolism = flower.symbolism, description = flower.description)
                        }
                        val flowerData = RecommendFlowerData(
                            flowerEvent = content.flowerEvent ?: false,
                            event = content.event ?: "",
                            flower = flowerInfo ?: FlowerInfo("", "", ""),
                            message = content.message ?: ""
                        )
                        callback(flowerData)
                    } ?: callback(null)
                } else {
                    Log.e("ChatGPT", "Response error: ${response.errorBody()?.string()}")
                    callback(null)
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.e("ChatGPT", "Network error: ${t.message}")
                callback(null)
            }
        })
    }

    fun getEventData(calendarEvent: String): RecommendFlowerData{
        val chatGPT = ChatGPT()
        val latch = CountDownLatch(1)
        var flowerData: RecommendFlowerData? = null

        chatGPT.getFlowerData(calendarEvent) { data ->
            flowerData = data
            latch.countDown() // 비동기 작업 완료 신호
        }

        latch.await() // 비동기 작업이 완료될 때까지 메인 쓰레드 대기

        flowerData?.let {
            println("Flower Event: ${it.flowerEvent}")
            println("Event: ${it.event}")
            println("Flower Name: ${it.flower.name}")
            println("Flower Symbolism: ${it.flower.symbolism}")
            println("Flower Description: ${it.flower.description}")
            println("Message: ${it.message}")
        } ?: println("Failed to retrieve flower data.")
        return flowerData!!
    }

    fun getFlowerImage(flowerData: RecommendFlowerData): Int{
        val flowerName = flowerData.flower.name
        Log.d("FlowerName: ",flowerName)
        return when(flowerName){
            "장미" -> R.drawable.rose_big
            "카네이션" -> R.drawable.cane_big
            "백합" -> R.drawable.back_big
            "진달래" -> R.drawable.pink_jindale

            else -> R.drawable.flower_combine1
        }
    }
    fun getFlowerImageSmaller(flowerData: RecommendFlowerData): Int{
        val flowerName = flowerData.flower.name
        Log.d("FlowerName: ",flowerName)
        return when(flowerName){
            "장미" -> R.drawable.fff4
            "카네이션" -> R.drawable.fff1
            "백합" -> R.drawable.fff3
            "진달래" -> R.drawable.fff5

            else -> R.drawable.fff5
        }
    }
}
data class FlowerInfo(
    var name: String,
    var symbolism: String,
    var description: String
)
data class RecommendFlowerData(
    var flowerEvent: Boolean,
    var event: String,
    var flower: FlowerInfo,
    var message: String
)

object LikeRecommend{
    var recommendList = ArrayList<RecommendFlowerData>()
    public fun putFlowerData(flowerData: RecommendFlowerData){
        recommendList.add(flowerData)
        Log.d("recommendList", recommendList.toString())
    }
    public fun deleteFlowerData(flowerData: RecommendFlowerData){
        recommendList.remove(flowerData)
        Log.d("recommendList", recommendList.toString())
    }
}

fun main() {
    val chatGPT = ChatGPT()
    chatGPT.getEventData("정안 생일")
}