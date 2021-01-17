package ru.fevgenson.timetable.shared.notifications.domain.entities

data class NotificationLessonEntity(
    val id: Long,
    val title: String,
    val text: String,
    val time: String
)