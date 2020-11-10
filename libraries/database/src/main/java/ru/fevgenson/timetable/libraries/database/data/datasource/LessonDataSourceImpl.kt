package ru.fevgenson.timetable.libraries.database.data.datasource

import kotlinx.coroutines.flow.Flow
import ru.fevgenson.timetable.libraries.database.data.generalDao.GeneralDao
import ru.fevgenson.timetable.shared.lesson.domain.entities.Lesson

internal class LessonDataSourceImpl(private val dao: GeneralDao) : LessonDataSource {

    override fun getLessons(
        weekType: Int,
        day: Int
    ): Flow<List<Lesson>> = dao.getLessonsForEdit(weekType, day)

    override suspend fun getLesson(id: Long): Lesson = dao.getLessonForEdit(id)

    override suspend fun saveLesson(lesson: Lesson) {
        dao.insertLesson(lesson)
    }

    override suspend fun updateLesson(lesson: Lesson) {
        dao.updateLesson(lesson)
    }

    override suspend fun deleteLesson(id: Long) {
        dao.deleteLesson(id)
    }
}