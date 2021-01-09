package ru.fevgenson.timetable.shared.lesson.domain.entity

data class TimeEntity(
    val id: Long,
    val time: String
) : CategoryEntity {

    override fun toSubcategoryEntity(): SubcategoryEntity = SubcategoryEntity(id, time)
}