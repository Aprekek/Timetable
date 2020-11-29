package ru.fevgenson.timetable.shared.lesson.domain.entity

data class TeacherEntity(
    val id: Long,
    val name: String,
    val phone: String?,
    val email: String?
)