package ru.fevgenson.timetable.features.notifications.presentation

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import ru.fevgenson.timetable.features.notifications.R
import ru.fevgenson.timetable.features.notifications.domain.entities.NotificationLesson
import ru.fevgenson.timetable.libraries.core.utils.dateutils.ShowedTimeUtils

internal object NotificationUtils {

    fun buildInitNotification(
        context: Context,
        channel: String
    ): Notification = NotificationCompat.Builder(context, channel)
        .setContentTitle(context.getString(R.string.notification_init_title))
        .setContentText(context.getString(R.string.notification_init_text))
        .setShowWhen(false)
        .setSmallIcon(R.drawable.ic_notification)
        .build()

    fun buildLessonNotification(
        context: Context,
        channel: String,
        notificationLesson: NotificationLesson?
    ): Notification = notificationLesson?.let {
        NotificationCompat.Builder(context, channel)
            .setContentTitle(notificationLesson.title)
            .setContentText(notificationLesson.text)
            .setSubText(ShowedTimeUtils.getShowedMinutesText(notificationLesson.time, context))
            .setShowWhen(false)
            .setSmallIcon(R.drawable.ic_notification)
            .build()
    } ?: buildEmptyNotification(context, channel)

    fun createChannel(
        channelId: String,
        channelName: String,
        context: Context
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = context.getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(serviceChannel)
        }
    }

    fun updateNotification(
        notificationId: Int,
        context: Context,
        notification: Notification
    ) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
        notificationManager?.notify(notificationId, notification)
    }

    private fun buildEmptyNotification(
        context: Context,
        channel: String
    ): Notification = NotificationCompat.Builder(context, channel)
        .setContentTitle(context.getString(R.string.notification_empty_title))
        .setContentText(context.getString(R.string.notification_empty_text))
        .setShowWhen(false)
        .setSmallIcon(R.drawable.ic_notification)
        .build()
}