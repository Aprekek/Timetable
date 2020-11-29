package ru.fevgenson.timetable.shared.lesson.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import ru.fevgenson.timetable.shared.lesson.data.datasource.LessonDataSource
import ru.fevgenson.timetable.shared.lesson.data.mapper.toDto
import ru.fevgenson.timetable.shared.lesson.data.mapper.toEntity
import ru.fevgenson.timetable.shared.lesson.domain.entity.LessonEntity
import ru.fevgenson.timetable.shared.lesson.domain.repository.LessonRepository

class LessonRepositoryImpl(
    private val dataSource: LessonDataSource
) : LessonRepository {

    override suspend fun getLesson(id: Long): LessonEntity =
        withContext(Dispatchers.IO) {
            dataSource.getLesson(id).toEntity()
        }

    override suspend fun getLessons(weekType: Int, day: Int): List<LessonEntity> =
        withContext(Dispatchers.IO) {
            dataSource.getLessons(weekType, day).map { it.toEntity() }
        }

    override fun getLessonsFlow(weekType: Int, day: Int): Flow<List<LessonEntity>> =
        dataSource.getLessonsFlow(weekType, day)
            .map { list -> list.map { it.toEntity() } }
            .flowOn(Dispatchers.IO)

    override suspend fun getAllLessons(): List<LessonEntity> =
        withContext(Dispatchers.IO) {
            dataSource.getAllLessons().map { it.toEntity() }
        }

    override fun getAllLessonsFlow(): Flow<List<LessonEntity>> =
        dataSource.getAllLessonsFlow()
            .map { list -> list.map { it.toEntity() } }
            .flowOn(Dispatchers.IO)

    override suspend fun saveLesson(lessonEntity: LessonEntity) {
        withContext(Dispatchers.IO) {
            dataSource.saveLesson(lessonEntity.toDto())
        }
    }

    override suspend fun deleteLesson(id: Long) {
        withContext(Dispatchers.IO) {
            dataSource.deleteLesson(id)
        }
    }

    override suspend fun deleteAllLessons() {
        withContext(Dispatchers.IO) {
            dataSource.deleteAllLessons()
        }
    }
}