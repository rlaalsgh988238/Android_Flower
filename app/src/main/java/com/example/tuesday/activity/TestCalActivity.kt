package com.example.tuesday.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CalendarView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tuesday.R
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException
import com.google.api.client.http.HttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.DateTime
import com.google.api.services.calendar.Calendar
import com.google.api.services.calendar.CalendarScopes
import com.google.api.services.calendar.model.Event
import com.google.api.services.calendar.model.Events
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.Date


class TestCalActivity : AppCompatActivity() {


    private lateinit var credential: GoogleAccountCredential
    private lateinit var transport: HttpTransport
    private lateinit var jsonFactory: JsonFactory
    private lateinit var calendarService: Calendar
    private val SCOPES = listOf(CalendarScopes.CALENDAR_READONLY)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_cal)

        // Google Calendar API 초기화
        transport = com.google.api.client.http.javanet.NetHttpTransport()
        jsonFactory = JacksonFactory.getDefaultInstance()
        credential = GoogleAccountCredential.usingOAuth2(this, SCOPES)

        // login_page에서 전달한 이메일을 받아 설정
        val email = intent.getStringExtra("email")
        val account=intent.getStringExtra("account")
        credential.selectedAccountName = email
        Log.d("GoogleAccountCredential", "Selected account: ${credential.selectedAccountName}")

        calendarService = Calendar.Builder(
            transport,
            jsonFactory,
            credential
        )
            .setApplicationName(getString(R.string.app_name))
            .build()

        // 사용자가 속한 모든 캘린더의 이벤트 가져오기
        loadEventsFromAllCalendars()
    }

    private fun loadEventsFromAllCalendars() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                // 사용자의 모든 캘린더 목록 가져오기
                val calendarList = calendarService.calendarList().list().execute().items
                Log.d("calendar",calendarList.toString())

                // 각 캘린더의 이벤트 가져오기
                val allEvents = mutableListOf<Event>()
                for (calendar in calendarList) {
                    val events: Events = calendarService.events().list(calendar.id)
                        .setMaxResults(10)
                        .setTimeMin(DateTime(System.currentTimeMillis()))
                        .setOrderBy("startTime")
                        .setSingleEvents(true)
                        .execute()
                    allEvents.addAll(events.items)
                }

                // 가져온 이벤트들을 출력하거나 처리하기
                for (event in allEvents) {
                    val start = event.start?.dateTime ?: event.start?.date
                    val end = event.end?.dateTime ?: event.end?.date

                    Log.d("Event summary", "${event.summary}")
                    Log.d("Event start", "$start")
                    Log.d("Event end", "$end")
                    println("Event summary: ${event.summary}")
                    println("Event start: $start")
                    println("Event end: $end")
                }

                // UI 업데이트는 메인 스레드에서 처리해야 함
                launch(Dispatchers.Main) {
                    // UI 업데이트 코드 작성
                }
            } catch (e: UserRecoverableAuthIOException) {
                // 사용자 인증 문제 처리
                Log.d("err", "UserRecoverableAuthIOException caught")
                startActivityForResult(e.intent, REQUEST_AUTHORIZATION)
                e.printStackTrace()
            } catch (e: IOException) {
                Log.d("err","err")
                e.printStackTrace()
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_AUTHORIZATION) {
            if (resultCode == Activity.RESULT_OK) {
                // 사용자가 권한을 승인했으므로 다시 시도
                loadEventsFromAllCalendars()
            } else {
                // 사용자가 권한을 승인하지 않음
                Log.d("err", "User did not authorize")
            }
        }
    }

    companion object {
        private const val REQUEST_AUTHORIZATION = 1001
        private val SCOPES = listOf(CalendarScopes.CALENDAR)
    }
}