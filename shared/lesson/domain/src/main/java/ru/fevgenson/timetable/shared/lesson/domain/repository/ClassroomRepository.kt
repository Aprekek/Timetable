package ru.fevgenson.timetable.shared.lesson.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.fevgenson.timetable.shared.lesson.domain.entity.ClassroomEntity

interface ClassroomRepository {

    suspend fun getClassroom(id: Long): ClassroomEntity
    suspend fun getAllClassrooms(): List<ClassroomEntity>
    fun getAllClassroomsFlow(): Flow<List<ClassroomEntity>>
    suspend fun saveClassroom(classroomEntity: ClassroomEntity)
    suspend fun deleteClassroom(id: Long)
    suspend fun deleteAllClassrooms()
}

