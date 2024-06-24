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
        // login_page에서 전달한 이메일을 받아 설정
        val email = intent.getStringExtra("email")
        // ViewPager 설정 및 초기화
        val name = intent.getStringExtra("name") // 사용자 이름 받아오기
        binding.viewPager.adapter = MainViewPagerAdapter(this, email, name)
        binding.viewPager.isUserInputEnabled = false
        binding.viewPager.post {
            binding.viewPager.currentItem = 1
        }
        // BottomNavigationView 설정
        binding.bottomNavigation.setSelectedItemId(R.id.menu_calendar)
        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> binding.viewPager.currentItem = 0
                R.id.menu_calendar -> binding.viewPager.currentItem = 1
                R.id.menu_bouquet -> binding.viewPager.currentItem = 2
            }
            true
        }
    }
    fun closeFragment() {
        finish()
    }
}
