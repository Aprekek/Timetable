package ru.fevgenson.timetable.shared.lesson.data.datasource

import kotlinx.coroutines.flow.Flow
import ru.fevgenson.timetable.shared.lesson.data.dto.ClassroomDto

interface ClassroomDataSource {

    suspend fun getClassroom(id: Long): ClassroomDto
    suspend fun getAllClassrooms(): List<ClassroomDto>
    fun getAllClassroomsFlow(): Flow<List<ClassroomDto>>
    suspend fun saveClassroom(classroomDto: ClassroomDto)
    suspend fun deleteClassroom(id: Long)
    suspend fun deleteAllClassrooms()
}

