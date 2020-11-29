package ru.fevgenson.timetable.shared.lesson.data.mapper

import ru.fevgenson.timetable.shared.lesson.data.dto.ClassroomDto
import ru.fevgenson.timetable.shared.lesson.domain.entity.ClassroomEntity

fun ClassroomEntity.toDto(): ClassroomDto = ClassroomDto(
    id = id,
    classroom = classroom,
)

fun ClassroomDto.toEntity(): ClassroomEntity = ClassroomEntity(
    id = id,
    classroom = classroom,
)