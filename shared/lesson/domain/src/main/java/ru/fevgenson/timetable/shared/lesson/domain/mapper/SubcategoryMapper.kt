package ru.fevgenson.timetable.shared.lesson.domain.mapper

import ru.fevgenson.timetable.shared.lesson.domain.entity.*

fun SubcategoryEntity.toSubjectEntity(): SubjectEntity = SubjectEntity(
    id = id,
    subject = description,
)

fun SubcategoryEntity.toClassroomEntity(): ClassroomEntity = ClassroomEntity(
    id = id,
    classroom = description,
)

fun SubcategoryEntity.toHousingEntity(): HousingEntity = HousingEntity(
    id = id,
    housing = description,
)

fun SubcategoryEntity.toTimeEntity(): TimeEntity = TimeEntity(
    id = id,
    time = description,
)

fun SubcategoryEntity.toTypeEntity(): TypeEntity = TypeEntity(
    id = id,
    type = description,
)