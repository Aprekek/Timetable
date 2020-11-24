package ru.fevgenson.timetable.shared.lesson.data.datasource

import kotlinx.coroutines.flow.Flow
import ru.fevgenson.timetable.shared.lesson.data.dao.CommonLessonDao
import ru.fevgenson.timetable.shared.lesson.data.dto.LessonDto

class LessonDataSourceImpl(
    private val dao: CommonLessonDao
) : LessonDataSource {

    override suspend fun getLesson(id: Long): LessonDto = dao.getLesson(id)

    override suspend fun getLessons(weekType: Int, day: Int): List<LessonDto> =
        dao.getLessons(weekType, day)

    override fun getLessonsFlow(weekType: Int, day: Int): Flow<List<LessonDto>> =
        dao.getLessonsFlow(weekType, day)

    override suspend fun getAllLessons(): List<LessonDto> = dao.getAllLessons()

    override fun getAllLessonsFlow(): Flow<List<LessonDto>> = dao.getAllLessonsFlow()

    override suspend fun saveLesson(lessonDto: LessonDto) {
        dao.insertLesson(lessonDto)
    }

    override suspend fun deleteLesson(id: Long) {
        dao.deleteLesson(id)
    }

    override suspend fun deleteAllLessons() {
        dao.deleteAllLessons()
    }
}