package ru.fevgenson.timetable.shared.lesson.data.mapper

import ru.fevgenson.timetable.shared.lesson.data.dto.SubjectDto
import ru.fevgenson.timetable.shared.lesson.domain.entity.SubjectEntity

fun SubjectEntity.toDto(): SubjectDto = SubjectDto(
    id = id,
    subject = subject,
)

fun SubjectDto.toEntity(): SubjectEntity = SubjectEntity(
    id = id,
    subject = subject,
)