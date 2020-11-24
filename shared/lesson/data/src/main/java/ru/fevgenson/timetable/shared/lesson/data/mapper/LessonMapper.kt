package ru.fevgenson.timetable.shared.lesson.data.mapper

import ru.fevgenson.timetable.shared.lesson.data.dto.LessonDto
import ru.fevgenson.timetable.shared.lesson.domain.entity.LessonEntity

fun LessonEntity.toDto(): LessonDto = LessonDto(
    id = id,
    subject = subject,
    time = time,
    housing = housing,
    classroom = classroom,
    type = type,
    teacher = teacher?.toDto(),
    day = day,
    weekType = weekType
)

fun LessonDto.toEntity(): LessonEntity = LessonEntity(
    id = id,
    subject = subject,
    time = time,
    housing = housing,
    classroom = classroom,
    type = type,
    teacher = teacher?.toEntity(),
    day = day,
    weekType = weekType
)