package com.example.tuesday

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tuesday.adapter.MainViewPagerAdapter
import com.example.tuesday.calendar.UtilObject
import com.example.tuesday.databinding.ActivityMainBinding
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException
import com.google.api.client.http.HttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.DateTime
import com.google.api.services.calendar.CalendarScopes
import com.google.api.services.calendar.model.Event
import com.google.api.services.calendar.model.Events
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var credential: GoogleAccountCredential
    private lateinit var transport: HttpTransport
    private lateinit var jsonFactory: JsonFactory
    private lateinit var calendarService: com.google.api.services.calendar.Calendar
    private val SCOPES = listOf(CalendarScopes.CALENDAR_READONLY)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        UtilObject.currentActivity = this
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Google Calendar API 초기화
//        transport = com.google.api.client.http.javanet.NetHttpTransport()
//        jsonFactory = JacksonFactory.getDefaultInstance()
//        credential = GoogleAccountCredential.usingOAuth2(this, SCOPES)

        // login_page에서 전달한 이메일을 받아 설정
        val email = intent.getStringExtra("email")
//        credential.selectedAccountName = email
//
//        calendarService = com.google.api.services.calendar.Calendar.Builder(
//            transport,
//            jsonFactory,
//            credential
//        )
//            .setApplicationName(resources.getString(R.string.app_name))
//            .build()
//
//        // 캘린더 데이터 가져오기 예시 (코루틴을 사용하여 백그라운드에서 실행)
//        GlobalScope.launch(Dispatchers.IO) {
//            try {
//                val events: Events = calendarService.events().list("primary")
//                    .setMaxResults(10)
//                    .setTimeMin(DateTime(System.currentTimeMillis()))
//                    .setOrderBy("startTime")
//                    .setSingleEvents(true)
//                    .execute()
//
//                val items: List<Event> = events.items
//                for (event in items) {
//                    println("Event summary: ${event.summary}")
//                }
//
//                // UI 업데이트는 메인 스레드에서 처리해야 함
//                launch(Dispatchers.Main) {
//                    // UI 업데이트 코드 작성
//                }
//            } catch (e: UserRecoverableAuthIOException) {
//                // 사용자 인증 문제 처리
//                e.printStackTrace()
//            } catch (e: IOException) {
//                e.printStackTrace()
//            }
//        }

        // ViewPager 설정 및 초기화
        val name = intent.getStringExtra("name") // 사용자 이름 받아오기
        binding.viewPager.adapter = MainViewPagerAdapter(this, email, name)
        binding.viewPager.isUserInputEnabled = false

        // BottomNavigationView 설정
        binding.bottomNavigation.setSelectedItemId(R.id.menu_home)
        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    binding.viewPager.currentItem = 0
                }
                R.id.menu_calendar -> binding.viewPager.currentItem = 1
                R.id.menu_bouquet -> binding.viewPager.currentItem = 2
            }
            true
        }
    }
}
