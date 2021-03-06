package ru.fevgenson.timetable.shared.lesson.domain.entity

data class HousingEntity(
    val id: Long,
    val housing: String
) : CategoryEntity {

    override fun toSubcategoryEntity(): SubcategoryEntity = SubcategoryEntity(id, housing)
}