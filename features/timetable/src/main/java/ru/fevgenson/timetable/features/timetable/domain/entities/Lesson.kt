package ru.fevgenson.timetable.features.timetable.domain.entities

data class Lesson(
    val id: Long = 0,
    val subject: String,
    val type: String?,
    val housing: String?,
    val classroom: String?,
    val teacher: String?,
    val time: String,
    val day: Int,
    val weekType: Int
)