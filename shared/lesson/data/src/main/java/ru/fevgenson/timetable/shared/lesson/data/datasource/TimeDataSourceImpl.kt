package ru.fevgenson.timetable.shared.lesson.data.datasource

import kotlinx.coroutines.flow.Flow
import ru.fevgenson.timetable.shared.lesson.data.dao.CommonLessonDao
import ru.fevgenson.timetable.shared.lesson.data.dto.TimeDto

class TimeDataSourceImpl(
    private val dao: CommonLessonDao
) : TimeDataSource {

    override suspend fun getTime(id: Long): TimeDto = dao.getTime(id)

    override suspend fun getAllTimes(): List<TimeDto> = dao.getAllTimes()

    override fun getAllTimesFlow(): Flow<List<TimeDto>> = dao.getAllTimesFlow()

    override suspend fun saveTime(timeDto: TimeDto) {
        dao.insertTime(timeDto)
    }

    override suspend fun deleteTime(id: Long) {
        dao.deleteTime(id)
    }

    override suspend fun deleteAllTimes() {
        dao.deleteAllTimes()
    }
}