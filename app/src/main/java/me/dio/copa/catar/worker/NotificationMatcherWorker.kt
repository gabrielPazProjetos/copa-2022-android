package me.dio.copa.catar.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import me.dio.copa.catar.R

class NotificationMatcherWorker(
    private val context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val matchName = inputData.getString("matchName") ?: return Result.failure()

        createNotificationChannel()

        val notification = NotificationCompat.Builder(context, "match_channel")
            .setSmallIcon(R.drawable.ic_notifications_active)
            .setContentTitle("Partida hoje!")
            .setContentText("Não perca: $matchName")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(matchName.hashCode(), notification)

        return Result.success()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "match_channel",
                "Notificações de Partidas",
                NotificationManager.IMPORTANCE_HIGH
            )
            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
}
