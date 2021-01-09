package ru.fevgenson.timetable.shared.lesson.domain.entity

data class SubjectEntity(
    val id: Long,
    val subject: String
) : CategoryEntity {

    override fun toSubcategoryEntity(): SubcategoryEntity = SubcategoryEntity(id, subject)
}