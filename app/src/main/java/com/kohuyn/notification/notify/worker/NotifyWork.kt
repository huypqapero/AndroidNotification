package com.kohuyn.notification.worker

import android.content.Context
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
        return success()
    }
}