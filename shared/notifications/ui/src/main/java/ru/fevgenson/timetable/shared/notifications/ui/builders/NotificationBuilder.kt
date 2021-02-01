package ru.fevgenson.timetable.shared.notifications.ui.builders

import android.app.Notification
import android.content.Context
import androidx.core.app.NotificationCompat
import ru.fevgenson.timetable.shared.notifications.domain.entities.NotificationLessonEntity
import ru.fevgenson.timetable.shared.notifications.ui.R
import ru.fevgenson.timetable.shared.notifications.ui.channels.Channels
import ru.fevgenson.timetable.shared.timeutils.ui.formatter.TimePositionFormatter

class NotificationBuilder(
    private val timePositionFormatter: TimePositionFormatter,
    private val context: Context,
    channel: Channels
) {

    private val channelId = context.getString(channel.idRes)

    fun buildInitNotification(): Notification =
        NotificationCompat.Builder(context, channelId)
            .setContentTitle(context.getString(R.string.notification_init_title))
            .setContentText(context.getString(R.string.notification_init_text))
            .setShowWhen(false)
            .setSmallIcon(R.drawable.ic_notification)
            .build()

    fun buildEmptyNotification(): Notification =
        NotificationCompat.Builder(context, channelId)
            .setContentTitle(context.getString(R.string.notification_empty_title))
            .setContentText(context.getString(R.string.notification_empty_text))
            .setShowWhen(false)
            .setSmallIcon(R.drawable.ic_notification)
            .build()

    fun buildLessonNotification(
        notificationLessonEntity: NotificationLessonEntity
    ): Notification =
        NotificationCompat.Builder(context, channelId)
            .setContentTitle(notificationLessonEntity.title)
            .setContentText(notificationLessonEntity.text)
            .setSubText(timePositionFormatter.format(notificationLessonEntity.time))
            .setShowWhen(false)
            .setSmallIcon(R.drawable.ic_notification)
            .build()
}