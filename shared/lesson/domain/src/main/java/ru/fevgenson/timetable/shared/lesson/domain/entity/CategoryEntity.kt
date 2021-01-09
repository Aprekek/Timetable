package ru.fevgenson.timetable.shared.lesson.domain.entity

interface CategoryEntity {

    fun toSubcategoryEntity(): SubcategoryEntity
}