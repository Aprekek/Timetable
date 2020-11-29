package ru.fevgenson.timetable.shared.lesson.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.fevgenson.timetable.shared.lesson.domain.entity.SubjectEntity

interface SubjectRepository {

    suspend fun getSubject(id: Long): SubjectEntity
    suspend fun getAllSubjects(): List<SubjectEntity>
    fun getAllSubjectsFlow(): Flow<List<SubjectEntity>>
    suspend fun saveSubject(subjectEntity: SubjectEntity)
    suspend fun deleteSubject(id: Long)
    suspend fun deleteAllSubjects()
}