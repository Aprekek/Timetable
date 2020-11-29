package ru.fevgenson.timetable.shared.lesson.data.datasource

import kotlinx.coroutines.flow.Flow
import ru.fevgenson.timetable.shared.lesson.data.dao.CommonLessonDao
import ru.fevgenson.timetable.shared.lesson.data.dto.ClassroomDto

class ClassroomDataSourceImpl(
    private val dao: CommonLessonDao
) : ClassroomDataSource {

    override suspend fun getClassroom(id: Long): ClassroomDto = dao.getClassroom(id)

    override suspend fun getAllClassrooms(): List<ClassroomDto> = dao.getAllClassrooms()

    override fun getAllClassroomsFlow(): Flow<List<ClassroomDto>> = dao.getAllClassroomsFlow()

    override suspend fun saveClassroom(classroomDto: ClassroomDto) {
        dao.insertClassroom(classroomDto)
    }

    override suspend fun deleteClassroom(id: Long) {
        dao.deleteClassroom(id)
    }

    override suspend fun deleteAllClassrooms() {
        dao.deleteAllClassrooms()
    }
}