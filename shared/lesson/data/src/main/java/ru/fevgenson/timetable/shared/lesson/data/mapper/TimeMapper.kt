package ru.fevgenson.timetable.shared.lesson.data.mapper

import ru.fevgenson.timetable.shared.lesson.data.dto.TimeDto
import ru.fevgenson.timetable.shared.lesson.domain.entity.TimeEntity

fun TimeEntity.toDto(): TimeDto = TimeDto(
    id = id,
    time = time,
)

fun TimeDto.toEntity(): TimeEntity = TimeEntity(
    id = id,
    time = time,
)