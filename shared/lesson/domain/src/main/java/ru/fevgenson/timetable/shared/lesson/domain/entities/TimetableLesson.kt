package ru.fevgenson.timetable.shared.lesson.domain.entities

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

fun List<Lesson>.toTimetableLessons(): List<TimetableLesson> = mapIndexed { index, lesson ->
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