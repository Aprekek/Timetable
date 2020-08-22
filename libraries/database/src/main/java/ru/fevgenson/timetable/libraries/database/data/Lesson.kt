package ru.fevgenson.timetable.libraries.database.data

data class Lesson(
    val id: Long,
    val subject: String,
    val time: String,
    val day: Int,
    val weekType: Int,
    val housing: String?,
    val classroom: String?,
    val type: String?,
    val teachersName: String?,
    val email: String?,
    val phone: String?
)