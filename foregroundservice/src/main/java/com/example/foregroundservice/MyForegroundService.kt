package com.example.foregroundservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat


class MyForegroundService : Service() {

    companion object {
        const val ACTION_SHOW_NOTIFICATION = "ACTION_SHOW_NOTIFICATION"
        const val NOTIFY_ID = 1
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        NotificationUtils.createNotificationChannel(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_SHOW_NOTIFICATION -> showNotification()
        }
        return START_STICKY
    }

    private fun showNotification() {
        val notification = NotificationCompat.Builder(this, NotificationUtils.CHANNEL_ID)
            .setContentTitle("Foreground Service")
            .setContentText("버튼이 눌려 알림이 표시되었습니다.")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()

        startForeground(NOTIFY_ID, notification)
    }
}
