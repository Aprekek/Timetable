package ru.fevgenson.timetable.shared.lesson.data.datasource

import kotlinx.coroutines.flow.Flow
import ru.fevgenson.timetable.shared.lesson.data.dto.TimeDto

interface TimeDataSource {

    suspend fun getTime(id: Long): TimeDto
    suspend fun getAllTimes(): List<TimeDto>
    fun getAllTimesFlow(): Flow<List<TimeDto>>
    suspend fun saveTime(timeDto: TimeDto)
    suspend fun deleteTime(id: Long)
    suspend fun deleteAllTimes()
}