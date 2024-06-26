package com.example.tuesday

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.tuesday.activity.PushActivity
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
        showNotification()
        binding.viewPager.isUserInputEnabled = false
        binding.viewPager.post {
            binding.viewPager.currentItem = 1
        }
        // BottomNavigationView 설정
        binding.bottomNavigation.setSelectedItemId(R.id.menu_calendar)
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
    fun closeFragment() {
        finish()
    }

    fun showNotification() {
        val CHANNEL_ID="tusday"
        var notiId=0
        val intent = Intent(this, PushActivity::class.java)
        intent.putExtra("push",true)

        val pendingIntent = PendingIntent.getActivity(this,0,intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_MUTABLE)

        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.tuesday_image)
            .setContentTitle("FelizCalendar")
            .setContentText("승주 생일이 얼마 안 남았어요! 생일 기념 꽃을 추천 받아보세요!")
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "FelizCalendar",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.description = "알림"
            channel.enableLights(true)
            channel.lightColor = Color.GRAY
            channel.enableVibration(true)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(notiId++ /* ID of notification */, notificationBuilder.build())

    }
}
