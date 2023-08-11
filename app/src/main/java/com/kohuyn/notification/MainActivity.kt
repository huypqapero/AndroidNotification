package com.kohuyn.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.kohuyn.notification.notify.factory.NotificationFactory
import com.kohuyn.notification.notify.receiver.NotificationReceiver
import com.kohuyn.notification.notify.worker.NotifyWork
import java.util.Calendar
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.tvClickAlarm).setOnClickListener {
            NotificationFactory.buildNotifyReminder(this)
//            setAlarmManagerAfter(15, TimeUnit.SECONDS)
//            setAlarmManagerSchedule()
            Log.d("MainActivity", "setAlarmManagerSchedule")
        }
    }

    private fun setAlarmManagerAfter(time: Long, timeUnit: TimeUnit) {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, NotificationReceiver::class.java)
//        intent.putExtra("task_info", taskInfo)
        val pendingIntent =
            PendingIntent.getBroadcast(this, 111, intent, PendingIntent.FLAG_IMMUTABLE)
        val mainActivityIntent = Intent(this, MainActivity::class.java)
        val basicPendingIntent =
            PendingIntent.getActivity(this, 123, mainActivityIntent, PendingIntent.FLAG_IMMUTABLE)
        val clockInfo = AlarmManager.AlarmClockInfo(
            System.currentTimeMillis().plus(timeUnit.toMillis(time)), basicPendingIntent
        )
        alarmManager.setAlarmClock(clockInfo, pendingIntent)
    }

    private fun setAlarmManagerSchedule() {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, NotificationReceiver::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(this, 111, intent, PendingIntent.FLAG_IMMUTABLE)
        val timeScheduleSetting = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 8)
            set(Calendar.MINUTE, 15)
            set(Calendar.SECOND, 15)
        }
        val runAfterMilis =
            maxOf(1, (timeScheduleSetting.timeInMillis - System.currentTimeMillis()))
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            runAfterMilis,
            TimeUnit.DAYS.toMillis(1),
            pendingIntent
        )
    }

    private fun setWorkManagerScheduler() {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 8)
        calendar.set(Calendar.MINUTE, 15)
        calendar.set(Calendar.SECOND, 20)
        val duration = maxOf(1, (calendar.timeInMillis - System.currentTimeMillis()))
        val notifyWorker = PeriodicWorkRequestBuilder<NotifyWork>(1, TimeUnit.HOURS)
            .setInitialDelay(
                duration,
                TimeUnit.MILLISECONDS
            )
            .addTag("work8h15")
            .build()
        WorkManager.getInstance(this).enqueue(notifyWorker)
        Toast.makeText(
            this,
            "setWorkManagerScheduler start after ${(duration / 1000).toInt()}s",
            Toast.LENGTH_SHORT
        ).show()
    }
}