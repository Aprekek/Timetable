package ru.fevgenson.timetable.features.notifications.domain.entities

internal data class NotificationLesson(
    val id: Long,
    val title: String,
    val text: String,
    val time: String
)