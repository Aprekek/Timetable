package ru.fevgenson.timetable.libraries.database.data

data class Lesson(

    val subject: String,
    val time: String,
    val day: Int,
    val weekType: Int,
    val housing: String? = null,
    val classroom: String? = null,
    val type: String? = null,
    val teachersName: String? = null,
    val email: String? = null,
    val phone: String? = null
)