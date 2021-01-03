package ru.fevgenson.timetable.features.timetable.domain.entities

import ru.fevgenson.timetable.shared.lesson.domain.entity.LessonEntity

data class TimetableLesson(
    val id: Long = 0,
    val subject: String,
    val type: String?,
    val housing: String?,
    val classroom: String?,
    val teacher: String?,
    val time: String,
    val day: Int,
    val weekType: Int,
    val position: Int
)

fun List<LessonEntity>.toTimetableLessons(): List<TimetableLesson> = mapIndexed { index, lesson ->
    TimetableLesson(
        id = lesson.id,
        subject = lesson.subject,
        type = lesson.type,
        housing = lesson.housing,
        classroom = lesson.classroom,
        teacher = lesson.teacher?.name,
        time = lesson.time,
        day = lesson.day,
        weekType = lesson.weekType,
        position = index
    )
}