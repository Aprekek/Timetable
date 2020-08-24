package ru.fevgenson.timetable.features.timetable.domain.entities

import ru.fevgenson.timetable.libraries.database.domain.entities.Lesson

data class TimetableLesson(
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

fun Lesson.toTimetableLesson(): TimetableLesson = TimetableLesson(
    id = id,
    subject = subject,
    type = type,
    housing = housing,
    classroom = classroom,
    teacher = teacher?.name,
    time = time,
    day = day,
    weekType = weekType
)