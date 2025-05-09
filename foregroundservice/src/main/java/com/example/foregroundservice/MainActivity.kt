package com.example.foregroundservice

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

// MainActivity.kt
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 알림 채널 생성 (앱 전체에서 한 번만)
        NotificationUtils.createNotificationChannel(this)

        val button: Button = findViewById(R.id.notifyButton)
        button.setOnClickListener {
            val intent = Intent(this, MyForegroundService::class.java).apply {
                action = MyForegroundService.ACTION_SHOW_NOTIFICATION
            }
            // 포그라운드 서비스 시작 (API 26+ 권장) :contentReference[oaicite:2]{index=2}
            ContextCompat.startForegroundService(this, intent)
        }
    }
}
