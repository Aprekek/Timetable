package ru.fevgenson.timetable.libraries.database.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.fevgenson.timetable.libraries.database.data.datasource.FieldsDataSource
import ru.fevgenson.timetable.libraries.database.data.tables.*
import ru.fevgenson.timetable.libraries.database.domain.repository.FieldsRepository

internal class FieldsRepositoryImpl(private val dataSource: FieldsDataSource) : FieldsRepository {

    override suspend fun getAllSubjects(): List<SubjectEntity> = withContext(Dispatchers.IO) {
        dataSource.getAllSubjects()
    }

    override suspend fun getAllTimes(): List<TimeEntity> = withContext(Dispatchers.IO) {
        dataSource.getAllTimes()
    }

    override suspend fun getAllHousings(): List<HousingEntity> = withContext(Dispatchers.IO) {
        dataSource.getAllHousings()
    }

    override suspend fun getAllClassrooms(): List<ClassroomEntity> = withContext(Dispatchers.IO) {
        dataSource.getAllClassrooms()
    }

    override suspend fun getAllTypes(): List<TypeEntity> = withContext(Dispatchers.IO) {
        dataSource.getAllTypes()
    }

    override suspend fun getAllTeachers(): List<TeacherEntity> = withContext(Dispatchers.IO) {
        dataSource.getAllTeachers()
    }
}