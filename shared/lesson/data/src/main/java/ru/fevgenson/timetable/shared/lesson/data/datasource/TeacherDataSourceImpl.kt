package ru.fevgenson.timetable.shared.lesson.data.datasource

import kotlinx.coroutines.flow.Flow
import ru.fevgenson.timetable.shared.lesson.data.dao.CommonLessonDao
import ru.fevgenson.timetable.shared.lesson.data.dto.TeacherDto

class TeacherDataSourceImpl(
    private val dao: CommonLessonDao
) : TeacherDataSource {

    override suspend fun getTeacher(id: Long): TeacherDto = dao.getTeacher(id)

    override suspend fun getAllTeachers(): List<TeacherDto> = dao.getAllTeachers()

    override fun getAllTeachersFlow(): Flow<List<TeacherDto>> = dao.getAllTeachersFlow()

    override suspend fun saveTeacher(teacherDto: TeacherDto) {
        dao.insertTeacher(teacherDto)
    }

    override suspend fun deleteTeacher(id: Long) {
        dao.deleteTeacher(id)
    }

    override suspend fun deleteAllTeachers() {
        dao.deleteAllTeachers()
    }
}