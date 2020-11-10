package ru.fevgenson.timetable.shared.lesson.domain.entities

data class Lesson(

    var id: Long = 0,
    var subject: String,
    var time: String,
    var day: Int,
    var weekType: Int,
    var housing: String? = null,
    var classroom: String? = null,
    var type: String? = null,
    var teacher: DomainTeacherEntity? = null
)