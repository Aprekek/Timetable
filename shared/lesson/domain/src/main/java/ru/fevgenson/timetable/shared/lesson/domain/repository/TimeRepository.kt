package ru.fevgenson.timetable.shared.lesson.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.fevgenson.timetable.shared.lesson.domain.entity.TimeEntity

interface TimeRepository {

    suspend fun getTime(id: Long): TimeEntity
    suspend fun getAllTimes(): List<TimeEntity>
    fun getAllTimesFlow(): Flow<List<TimeEntity>>
    suspend fun saveTime(timeEntity: TimeEntity)
    suspend fun deleteTime(id: Long)
    suspend fun deleteAllTimes()
}