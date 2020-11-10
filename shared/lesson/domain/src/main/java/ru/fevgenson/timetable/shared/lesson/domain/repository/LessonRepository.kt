package ru.fevgenson.timetable.shared.lesson.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.fevgenson.timetable.shared.lesson.domain.entities.Lesson

interface LessonRepository {

    fun getLessons(weekType: Int, day: Int): Flow<List<Lesson>>
    suspend fun getLesson(id: Long): Lesson
    suspend fun saveLesson(lesson: Lesson)
    suspend fun updateLesson(lesson: Lesson)
    suspend fun deleteLesson(id: Long)
}