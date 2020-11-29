package ru.fevgenson.timetable.shared.lesson.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.fevgenson.timetable.shared.lesson.domain.entity.TeacherEntity

interface TeacherRepository {

    suspend fun getTeacher(id: Long): TeacherEntity
    suspend fun getAllTeachers(): List<TeacherEntity>
    fun getAllTeachersFlow(): Flow<List<TeacherEntity>>
    suspend fun saveTeacher(teacherEntity: TeacherEntity)
    suspend fun deleteTeacher(id: Long)
    suspend fun deleteAllTeachers()
}