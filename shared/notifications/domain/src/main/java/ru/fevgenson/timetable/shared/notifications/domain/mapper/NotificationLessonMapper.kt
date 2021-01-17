package ru.fevgenson.timetable.shared.notifications.domain.mapper

import ru.fevgenson.timetable.shared.lesson.domain.entity.LessonEntity
import ru.fevgenson.timetable.shared.notifications.domain.entities.NotificationLessonEntity

fun List<LessonEntity>.toNotificationLessonEntities(): List<NotificationLessonEntity> =
    map { it.toNotificationLessonEntity() }

fun LessonEntity.toNotificationLessonEntity(): NotificationLessonEntity =
    NotificationLessonEntity(
        id = id,
        title = "$subject($time)",
        text = createTextString(housing, classroom, type),
        time = time
    )

private fun createTextString(
    housing: String?,
    classroom: String?,
    type: String?
): String = StringBuilder().apply {
    housing?.let(::append)
    classroom?.let {
        if (isEmpty()) {
            append(it)
        } else {
            append("($it)")
        }
    }
    type?.let {
        if (isEmpty()) {
            append(it)
        } else {
            append(", $it")
        }
    }
}.toString()