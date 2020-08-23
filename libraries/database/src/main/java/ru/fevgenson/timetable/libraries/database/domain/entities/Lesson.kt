package ru.fevgenson.timetable.libraries.database.domain.entities

data class Lesson(

    var id: Long = 0,
    var subject: String,
    var time: String,
    var day: Int,
    var weekType: Int,
    var housing: String? = null,
    var classroom: String? = null,
    var type: String? = null,
    var teachersName: String? = null,
    var email: String? = null,
    var phone: String? = null
)