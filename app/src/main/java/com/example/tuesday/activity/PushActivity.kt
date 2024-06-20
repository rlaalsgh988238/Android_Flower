package com.example.tuesday.activity

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.tuesday.MainActivity
import com.example.tuesday.R
import com.example.tuesday.databinding.ActivityPushBinding

class PushActivity : AppCompatActivity() {
    lateinit var binding: ActivityPushBinding
    var notiId = 0

    private val CHANNEL_ID = "TuesDay"
    private val NOTIFICATION_ID = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPushBinding.inflate(layoutInflater)

        val intentMain = Intent(this, MainActivity::class.java)

//        binding.push.setOnClickListener{
//            showNotification()
//        }

        binding.main.setOnClickListener {
            startActivity(intentMain)
        }

        setContentView(binding.root)
    }
























    fun showNotification() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("push",true)
        val pendingIntent = PendingIntent.getActivity(this,0,intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_MUTABLE)

        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.tuesday_image)
            .setContentTitle("FelizCalendar")
            .setContentText("결혼기념일이 얼마 안남았어요! 어울리는 꽃을 준비해 보아요")
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