package ru.fevgenson.timetable.libraries.database.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.fevgenson.timetable.libraries.database.domain.entities.Lesson

interface LessonRepository {

    fun getLessons(weekType: Int, day: Int): Flow<List<Lesson>>
    suspend fun getLesson(id: Long): Lesson
    suspend fun saveLesson(lesson: Lesson)
    suspend fun updateLesson(lesson: Lesson)
    suspend fun deleteLesson(id: Long)
}