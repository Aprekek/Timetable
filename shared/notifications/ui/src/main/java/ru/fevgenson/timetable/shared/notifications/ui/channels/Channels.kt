package ru.fevgenson.timetable.shared.notifications.ui.channels

import android.app.NotificationManager
import android.os.Build
import ru.fevgenson.timetable.shared.notifications.ui.R

private const val IMPORTANCE_UNSUPPORTED = 0

enum class Channels(
    val idRes: Int,
    val nameRes: Int,
    val importance: Int
) {

    FOREGROUND_CHANNEL(
        idRes = R.string.notification_foreground_channel_id,
        nameRes = R.string.notification_foreground_channel_id,
        importance = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager.IMPORTANCE_LOW
        } else {
            IMPORTANCE_UNSUPPORTED
        }
    )
}