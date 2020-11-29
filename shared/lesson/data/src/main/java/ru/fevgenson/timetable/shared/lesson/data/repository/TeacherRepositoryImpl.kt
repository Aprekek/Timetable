package ru.fevgenson.timetable.shared.lesson.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import ru.fevgenson.timetable.shared.lesson.data.datasource.TeacherDataSource
import ru.fevgenson.timetable.shared.lesson.data.mapper.toDto
import ru.fevgenson.timetable.shared.lesson.data.mapper.toEntity
import ru.fevgenson.timetable.shared.lesson.domain.entity.TeacherEntity
import ru.fevgenson.timetable.shared.lesson.domain.repository.TeacherRepository

class TeacherRepositoryImpl(
    private val dataSource: TeacherDataSource
) : TeacherRepository {

    override suspend fun getTeacher(id: Long): TeacherEntity =
        withContext(Dispatchers.IO) {
            dataSource.getTeacher(id).toEntity()
        }

    override suspend fun getAllTeachers(): List<TeacherEntity> =
        withContext(Dispatchers.IO) {
            dataSource.getAllTeachers().map { it.toEntity() }
        }

    override fun getAllTeachersFlow(): Flow<List<TeacherEntity>> =
        dataSource.getAllTeachersFlow()
            .map { list -> list.map { it.toEntity() } }
            .flowOn(Dispatchers.IO)

    override suspend fun saveTeacher(teacherEntity: TeacherEntity) {
        withContext(Dispatchers.IO) {
            dataSource.saveTeacher(teacherEntity.toDto())
        }
    }

    override suspend fun deleteTeacher(id: Long) {
        withContext(Dispatchers.IO) {
            dataSource.deleteTeacher(id)
        }
    }

    override suspend fun deleteAllTeachers() {
        withContext(Dispatchers.IO) {
            dataSource.deleteAllTeachers()
        }
    }
}