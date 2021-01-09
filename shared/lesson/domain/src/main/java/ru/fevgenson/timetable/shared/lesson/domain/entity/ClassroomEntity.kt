package ru.fevgenson.timetable.shared.lesson.domain.entity

data class ClassroomEntity(
    val id: Long,
    val classroom: String
) : CategoryEntity {

    override fun toSubcategoryEntity(): SubcategoryEntity = SubcategoryEntity(id, classroom)
}