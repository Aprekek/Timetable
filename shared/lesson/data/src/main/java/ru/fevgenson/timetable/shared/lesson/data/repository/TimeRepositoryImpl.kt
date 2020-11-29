package ru.fevgenson.timetable.shared.lesson.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import ru.fevgenson.timetable.shared.lesson.data.datasource.TimeDataSource
import ru.fevgenson.timetable.shared.lesson.data.mapper.toDto
import ru.fevgenson.timetable.shared.lesson.data.mapper.toEntity
import ru.fevgenson.timetable.shared.lesson.domain.entity.TimeEntity
import ru.fevgenson.timetable.shared.lesson.domain.repository.TimeRepository

class TimeRepositoryImpl(
    private val dataSource: TimeDataSource
) : TimeRepository {

    override suspend fun getTime(id: Long): TimeEntity =
        withContext(Dispatchers.IO) {
            dataSource.getTime(id).toEntity()
        }

    override suspend fun getAllTimes(): List<TimeEntity> =
        withContext(Dispatchers.IO) {
            dataSource.getAllTimes().map { it.toEntity() }
        }

    override fun getAllTimesFlow(): Flow<List<TimeEntity>> =
        dataSource.getAllTimesFlow()
            .map { list -> list.map { it.toEntity() } }
            .flowOn(Dispatchers.IO)

    override suspend fun saveTime(timeEntity: TimeEntity) {
        withContext(Dispatchers.IO) {
            dataSource.saveTime(timeEntity.toDto())
        }
    }

    override suspend fun deleteTime(id: Long) {
        withContext(Dispatchers.IO) {
            dataSource.deleteTime(id)
        }
    }

    override suspend fun deleteAllTimes() {
        withContext(Dispatchers.IO) {
            dataSource.deleteAllTimes()
        }
    }
}