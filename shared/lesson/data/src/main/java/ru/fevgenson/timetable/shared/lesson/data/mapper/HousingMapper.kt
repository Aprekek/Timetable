package ru.fevgenson.timetable.shared.lesson.data.mapper

import ru.fevgenson.timetable.shared.lesson.data.dto.HousingDto
import ru.fevgenson.timetable.shared.lesson.domain.entity.HousingEntity

fun HousingEntity.toDto(): HousingDto = HousingDto(
    id = id,
    housing = housing,
)

fun HousingDto.toEntity(): HousingEntity = HousingEntity(
    id = id,
    housing = housing,
)