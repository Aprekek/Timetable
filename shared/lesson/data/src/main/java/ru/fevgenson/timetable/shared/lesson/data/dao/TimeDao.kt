package ru.fevgenson.timetable.shared.lesson.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.fevgenson.timetable.shared.lesson.data.dto.TimeDto

@Dao
interface TimeDao {

    suspend fun getTimeIdOrInsert(time: String): Long =
        getTime(time)?.id ?: insertTime(TimeDto(time = time))

    @Query("SELECT * from time_table")
    suspend fun getAllTimes(): List<TimeDto>

    @Query("SELECT * from time_table")
    fun getAllTimesFlow(): Flow<List<TimeDto>>

    @Query("SELECT * from time_table WHERE time = :time")
    suspend fun getTime(time: String): TimeDto?

    @Query("SELECT * from time_table WHERE time_id = :id")
    suspend fun getTime(id: Long): TimeDto

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTime(time: TimeDto): Long

    @Query("DELETE from time_table WHERE time_id = :id")
    suspend fun deleteTime(id: Long)

    @Query("DELETE from time_table")
    suspend fun deleteAllTimes()
}