package ru.fevgenson.timetable.libraries.database.data.datasource

import kotlinx.coroutines.flow.Flow
import ru.fevgenson.timetable.shared.lesson.domain.entities.Lesson

internal interface LessonDataSource {

    fun getLessonsByDay(weekType: Int, day: Int): Flow<List<Lesson>>
    fun getLessonsBySubcategory(category: Int, subcategoryName: String): Flow<List<Lesson>>
    suspend fun getLesson(id: Long): Lesson
    suspend fun saveLesson(lesson: Lesson)
    suspend fun updateLesson(lesson: Lesson)
    suspend fun deleteLesson(id: Long)
}