package com.example.tuesday.AI.model

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

data class Message(val role: String, var content: String)
data class RequestBody(val model: String, val messages: List<Message>)
data class Flower(val name: String, val symbolism: String, val description: String)
data class ResponseContent(
    @SerializedName("flowerEvent") val flowerEvent: Boolean?,
    @SerializedName("event") val event: String?,
    @SerializedName("flower") val flower: Flower?,
    @SerializedName("message") val message: String?
)
data class Choice(val index: Int, val message: Message)
data class ApiResponse(val id: String, val `object`: String, val created: Long, val model: String, val choices: List<Choice>, val usage: Usage, val system_fingerprint: String?)
data class Usage(val prompt_tokens: Int, val completion_tokens: Int, val total_tokens: Int)

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("v1/chat/completions")
    fun createRequest(
        @Body body: RequestBody
    ): Call<ApiResponse>
}