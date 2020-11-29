package ru.fevgenson.timetable.shared.lesson.data.datasource

import kotlinx.coroutines.flow.Flow
import ru.fevgenson.timetable.shared.lesson.data.dto.LessonDto

interface LessonDataSource {

    suspend fun getLesson(id: Long): LessonDto
    suspend fun getLessons(weekType: Int, day: Int): List<LessonDto>
    fun getLessonsFlow(weekType: Int, day: Int): Flow<List<LessonDto>>
    suspend fun getAllLessons(): List<LessonDto>
    fun getAllLessonsFlow(): Flow<List<LessonDto>>
    suspend fun saveLesson(lessonDto: LessonDto)
    suspend fun deleteLesson(id: Long)
    suspend fun deleteAllLessons()
}