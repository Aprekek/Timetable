package ru.fevgenson.timetable.shared.lesson.data.mapper

import ru.fevgenson.timetable.shared.lesson.data.dto.TypeDto
import ru.fevgenson.timetable.shared.lesson.domain.entity.TypeEntity

fun TypeEntity.toDto(): TypeDto = TypeDto(
    id = id,
    type = type,
)

fun TypeDto.toEntity(): TypeEntity = TypeEntity(
    id = id,
    type = type,
)