package ru.fevgenson.timetable.shared.lesson.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import ru.fevgenson.timetable.shared.lesson.data.datasource.ClassroomDataSource
import ru.fevgenson.timetable.shared.lesson.data.mapper.toDto
import ru.fevgenson.timetable.shared.lesson.data.mapper.toEntity
import ru.fevgenson.timetable.shared.lesson.domain.entity.ClassroomEntity
import ru.fevgenson.timetable.shared.lesson.domain.repository.ClassroomRepository

class ClassroomRepositoryImpl(
    private val dataSource: ClassroomDataSource
) : ClassroomRepository {

    override suspend fun getClassroom(id: Long): ClassroomEntity =
        withContext(Dispatchers.IO) {
            dataSource.getClassroom(id).toEntity()
        }

    override suspend fun getAllClassrooms(): List<ClassroomEntity> =
        withContext(Dispatchers.IO) {
            dataSource.getAllClassrooms().map { it.toEntity() }
        }

    override fun getAllClassroomsFlow(): Flow<List<ClassroomEntity>> =
        dataSource.getAllClassroomsFlow()
            .map { list -> list.map { it.toEntity() } }
            .flowOn(Dispatchers.IO)

    override suspend fun saveClassroom(classroomEntity: ClassroomEntity) {
        withContext(Dispatchers.IO) {
            dataSource.saveClassroom(classroomEntity.toDto())
        }
    }

    override suspend fun deleteClassroom(id: Long) {
        withContext(Dispatchers.IO) {
            dataSource.deleteClassroom(id)
        }
    }

    override suspend fun deleteAllClassrooms() {
        withContext(Dispatchers.IO) {
            dataSource.deleteAllClassrooms()
        }
    }
}