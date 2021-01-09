package ru.fevgenson.timetable.shared.lesson.domain.entity

data class TypeEntity(
    val id: Long,
    val type: String
) : CategoryEntity {

    override fun toSubcategoryEntity(): SubcategoryEntity = SubcategoryEntity(id, type)
}