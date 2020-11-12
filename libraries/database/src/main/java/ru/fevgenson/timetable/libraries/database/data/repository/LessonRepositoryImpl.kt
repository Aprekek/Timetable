package ru.fevgenson.timetable.libraries.database.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import ru.fevgenson.timetable.libraries.database.data.datasource.LessonDataSource
import ru.fevgenson.timetable.shared.lesson.domain.entities.Lesson
import ru.fevgenson.timetable.shared.lesson.domain.repository.LessonRepository

internal class LessonRepositoryImpl(private val dataSource: LessonDataSource) : LessonRepository {

    override fun getLessonsByDay(
        weekType: Int,
        day: Int
    ): Flow<List<Lesson>> = dataSource.getLessonsByDay(weekType, day)
        .flowOn(Dispatchers.IO)

    override fun getLessonsBySubcategory(
        category: Int,
        subcategoryName: String
    ): Flow<List<Lesson>> = dataSource.getLessonsBySubcategory(category, subcategoryName)

    override suspend fun getLesson(id: Long): Lesson = withContext(Dispatchers.IO) {
        dataSource.getLesson(id)
    }

    override suspend fun saveLesson(lesson: Lesson) {
        withContext(Dispatchers.IO) {
            dataSource.saveLesson(lesson)
        }
    }

    override suspend fun updateLesson(lesson: Lesson) {
        withContext(Dispatchers.IO) {
            dataSource.updateLesson(lesson)
        }
    }

    override suspend fun deleteLesson(id: Long) {
        withContext(Dispatchers.IO) {
            dataSource.deleteLesson(id)
        }
    }
}