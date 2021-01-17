package ru.fevgenson.timetable.shared.notifications.ui.builders

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import ru.fevgenson.timetable.shared.notifications.ui.channels.Channels

class ChannelBuilder(
    private val context: Context
) {

    fun createChannel(channel: Channels) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val androidChannel = NotificationChannel(
                context.getString(channel.idRes),
                context.getString(channel.nameRes),
                channel.importance
            )
            val manager = context.getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(androidChannel)
        }
    }
}