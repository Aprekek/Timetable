package ru.fevgenson.timetable.shared.lesson.data.datasource

import kotlinx.coroutines.flow.Flow
import ru.fevgenson.timetable.shared.lesson.data.dto.SubjectDto

interface SubjectDataSource {

    suspend fun getSubject(id: Long): SubjectDto
    suspend fun getAllSubjects(): List<SubjectDto>
    fun getAllSubjectsFlow(): Flow<List<SubjectDto>>
    suspend fun saveSubject(subjectDto: SubjectDto)
    suspend fun deleteSubject(id: Long)
    suspend fun deleteAllSubjects()
}