package ru.fevgenson.timetable.shared.lesson.data.mapper

import ru.fevgenson.timetable.shared.lesson.data.dto.*
import ru.fevgenson.timetable.shared.lesson.domain.entity.SubcategoryEntity

fun SubjectDto.toSubcategoryEntity(): SubcategoryEntity = SubcategoryEntity(
    id = id,
    description = subject,
)

fun SubcategoryEntity.toSubjectDto(): SubjectDto = SubjectDto(
    id = id,
    subject = description,
)

fun TeacherDto.toSubcategoryEntity(): SubcategoryEntity = SubcategoryEntity(
    id = id,
    description = name,
)

fun SubcategoryEntity.toTeacherDto(): TeacherDto = TeacherDto(
    id = id,
    name = description,
)

fun ClassroomDto.toSubcategoryEntity(): SubcategoryEntity = SubcategoryEntity(
    id = id,
    description = classroom,
)

fun SubcategoryEntity.toClassroomDto(): ClassroomDto = ClassroomDto(
    id = id,
    classroom = description,
)

fun HousingDto.toSubcategoryEntity(): SubcategoryEntity = SubcategoryEntity(
    id = id,
    description = housing,
)

fun SubcategoryEntity.toHousingDto(): HousingDto = HousingDto(
    id = id,
    housing = description,
)

fun TimeDto.toSubcategoryEntity(): SubcategoryEntity = SubcategoryEntity(
    id = id,
    description = time,
)

fun SubcategoryEntity.toTimeDto(): TimeDto = TimeDto(
    id = id,
    time = description,
)

fun TypeDto.toSubcategoryEntity(): SubcategoryEntity = SubcategoryEntity(
    id = id,
    description = type,
)

fun SubcategoryEntity.toTypeDto(): TypeDto = TypeDto(
    id = id,
    type = description,
)