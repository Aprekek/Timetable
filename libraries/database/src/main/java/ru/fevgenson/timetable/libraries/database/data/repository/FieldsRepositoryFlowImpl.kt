package ru.fevgenson.timetable.libraries.database.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.fevgenson.timetable.libraries.database.data.datasource.FieldsDataSourceFlow
import ru.fevgenson.timetable.libraries.database.data.tables.toDomainTeacherEntity
import ru.fevgenson.timetable.shared.lesson.domain.entities.DomainTeacherEntity
import ru.fevgenson.timetable.shared.lesson.domain.repository.FieldsRepositoryFlow

internal class FieldsRepositoryFlowImpl(private val dataSource: FieldsDataSourceFlow) :
    FieldsRepositoryFlow {

    override fun getAllSubjects(): Flow<List<String>> =
        dataSource.getAllSubjects().map { list ->
            list.map { it.subject }
        }

    override fun getAllTimes(): Flow<List<String>> =
        dataSource.getAllTimes().map { list ->
            list.map { it.time }
        }

    override fun getAllHousings(): Flow<List<String>> =
        dataSource.getAllHousings().map { list ->
            list.map { it.housing }
        }

    override fun getAllClassrooms(): Flow<List<String>> =
        dataSource.getAllClassrooms().map { list ->
            list.map { it.classroom }
        }

    override fun getAllTypes(): Flow<List<String>> =
        dataSource.getAllTypes().map { list ->
            list.map { it.type }
        }

    override fun getAllTeachers(): Flow<List<DomainTeacherEntity>> =
        dataSource.getAllTeachers().map { list ->
            list.map { it.toDomainTeacherEntity() }
        }
}