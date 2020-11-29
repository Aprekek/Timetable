package ru.fevgenson.timetable.shared.lesson.data.datasource

import kotlinx.coroutines.flow.Flow
import ru.fevgenson.timetable.shared.lesson.data.dto.TeacherDto

interface TeacherDataSource {

    suspend fun getTeacher(id: Long): TeacherDto
    suspend fun getAllTeachers(): List<TeacherDto>
    fun getAllTeachersFlow(): Flow<List<TeacherDto>>
    suspend fun saveTeacher(teacherDto: TeacherDto)
    suspend fun deleteTeacher(id: Long)
    suspend fun deleteAllTeachers()
}