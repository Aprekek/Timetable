package ru.fevgenson.timetable.features.dictionary.domain

object Categories {

    enum class CategoryTypes(val value: Int) {
        SUBJECT_CATEGORY(0),
        TEACHER_CATEGORY(1),
        CLASSROOM_CATEGORY(2),
        HOUSING_CATEGORY(3),
        TIME_CATEGORY(4),
        TYPE_CATEGORY(5)
    }

    const val TOTAL_CATEGORIES = 6
}