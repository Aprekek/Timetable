package ru.fevgenson.timetable.libraries.database.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.fevgenson.timetable.libraries.database.data.datasource.FieldsDataSource
import ru.fevgenson.timetable.libraries.database.data.tables.toDomainTeacherEntity
import ru.fevgenson.timetable.shared.lesson.domain.entities.DomainTeacherEntity
import ru.fevgenson.timetable.shared.lesson.domain.repository.FieldsRepository

internal class FieldsRepositoryImpl(private val dataSource: FieldsDataSource) : FieldsRepository {

    override suspend fun getAllSubjects(): List<String> = withContext(Dispatchers.IO) {
        dataSource.getAllSubjects().map { it.subject }
    }

    override suspend fun getAllTimes(): List<String> = withContext(Dispatchers.IO) {
        dataSource.getAllTimes().map { it.time }
    }

    override suspend fun getAllHousings(): List<String> = withContext(Dispatchers.IO) {
        dataSource.getAllHousings().map { it.housing }
    }

    override suspend fun getAllClassrooms(): List<String> = withContext(Dispatchers.IO) {
        dataSource.getAllClassrooms().map { it.classroom }
    }

    override suspend fun getAllTypes(): List<String> = withContext(Dispatchers.IO) {
        dataSource.getAllTypes().map { it.type }
    }

    //Entity?
    override suspend fun getAllTeachers(): List<DomainTeacherEntity> = withContext(Dispatchers.IO) {
        dataSource.getAllTeachers().map { it.toDomainTeacherEntity() }
    }
}