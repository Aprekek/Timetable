package ru.fevgenson.timetable.shared.lesson.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import ru.fevgenson.timetable.shared.lesson.data.datasource.SubjectDataSource
import ru.fevgenson.timetable.shared.lesson.data.mapper.toDto
import ru.fevgenson.timetable.shared.lesson.data.mapper.toEntity
import ru.fevgenson.timetable.shared.lesson.domain.entity.SubjectEntity
import ru.fevgenson.timetable.shared.lesson.domain.repository.SubjectRepository

class SubjectRepositoryImpl(
    private val dataSource: SubjectDataSource
) : SubjectRepository {

    override suspend fun getSubject(id: Long): SubjectEntity =
        withContext(Dispatchers.IO) {
            dataSource.getSubject(id).toEntity()
        }

    override suspend fun getAllSubjects(): List<SubjectEntity> =
        withContext(Dispatchers.IO) {
            dataSource.getAllSubjects().map { it.toEntity() }
        }

    override fun getAllSubjectsFlow(): Flow<List<SubjectEntity>> =
        dataSource.getAllSubjectsFlow()
            .map { list -> list.map { it.toEntity() } }
            .flowOn(Dispatchers.IO)

    override suspend fun saveSubject(subjectEntity: SubjectEntity) {
        withContext(Dispatchers.IO) {
            dataSource.saveSubject(subjectEntity.toDto())
        }
    }

    override suspend fun deleteSubject(id: Long) {
        withContext(Dispatchers.IO) {
            dataSource.deleteSubject(id)
        }
    }

    override suspend fun deleteAllSubjects() {
        withContext(Dispatchers.IO) {
            dataSource.deleteAllSubjects()
        }
    }
}