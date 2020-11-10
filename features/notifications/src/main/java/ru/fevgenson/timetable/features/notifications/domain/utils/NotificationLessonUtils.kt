package ru.fevgenson.timetable.features.notifications.domain.utils

import ru.fevgenson.timetable.features.notifications.domain.entities.NotificationLesson
import ru.fevgenson.timetable.libraries.core.utils.dateutils.MyTimeUtils
import ru.fevgenson.timetable.shared.lesson.domain.entities.Lesson

internal fun List<NotificationLesson>.getCurrentLesson(): NotificationLesson? = find {
    val currentTime = MyTimeUtils.getCurrentTime()
    val startTime = MyTimeUtils.convertDbTimeToMinutes(it.time, MyTimeUtils.TimeBorders.START)
    val endTime = MyTimeUtils.convertDbTimeToMinutes(it.time, MyTimeUtils.TimeBorders.END)
    currentTime < startTime || currentTime in startTime..endTime
}

internal fun Lesson.toNotificationLesson(): NotificationLesson =
    NotificationLesson(
        id = id,
        title = "$subject($time)",
        text = createTextString(housing, classroom, type),
        time = time
    )

private fun createTextString(
    housing: String?,
    classroom: String?,
    type: String?
): String {
    val result = StringBuilder()
    housing?.let {
        result.append(it)
    }
    classroom?.let {
        if (result.isEmpty()) {
            result.append(it)
        } else {
            result.append("($it)")
        }
    }
    type?.let {
        if (result.isEmpty()) {
            result.append(it)
        } else {
            result.append(", $it")
        }
    }
    return result.toString()
}