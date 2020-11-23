package ru.fevgenson.timetable.shared.lesson.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.fevgenson.timetable.shared.lesson.domain.entity.LessonEntity

interface LessonRepository {

    suspend fun getLesson(id: Long): LessonEntity
    suspend fun getLessons(weekType: Int, day: Int): List<LessonEntity>
    fun getLessonsFlow(weekType: Int, day: Int): Flow<List<LessonEntity>>
    suspend fun getAllLessons(): List<LessonEntity>
    fun getAllLessonsFlow(): Flow<List<LessonEntity>>
    suspend fun saveLesson(lessonEntity: LessonEntity)
    suspend fun deleteLesson(id: Long)
    suspend fun deleteAllLessons()
}