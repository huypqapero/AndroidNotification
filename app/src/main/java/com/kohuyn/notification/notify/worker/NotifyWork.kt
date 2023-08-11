package com.kohuyn.notification.notify.worker

import android.content.Context
import android.util.Log
import androidx.work.ListenableWorker.Result.success
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.kohuyn.notification.notify.factory.NotificationFactory

/**
 * Created by KO Huyn on 11/08/2023.
 */
class NotifyWork(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        NotificationFactory.buildNotifyReminder(applicationContext)
        Log.d("NotifyWork","Show Notification")
        return success()
    }
}