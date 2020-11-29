package ru.fevgenson.timetable.shared.lesson.data.mapper

import ru.fevgenson.timetable.shared.lesson.data.dto.TeacherDto
import ru.fevgenson.timetable.shared.lesson.domain.entity.TeacherEntity

fun TeacherEntity.toDto(): TeacherDto = TeacherDto(
    id = id,
    name = name,
    email = email,
    phone = phone,
)

fun TeacherDto.toEntity(): TeacherEntity = TeacherEntity(
    id = id,
    name = name,
    email = email,
    phone = phone,
)