package ru.fevgenson.timetable.libraries.database.data.tables

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "time_table", indices = [Index(value = ["time"], unique = true)])
data class TimeEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val time: String
)

data class TimeLessonRelations(
    var id: Long,

    @Relation(parentColumn = "id", entityColumn = "time")
    var lessons: List<LessonEntity>
)

@Dao
internal interface TimeDao {

    @Transaction
    @Query("SELECT id from time_table WHERE time =:time")
    fun getLessonsByTime(time: String): Flow<TimeLessonRelations>

    @Query("SELECT * from time_table")
    suspend fun getTimes(): List<TimeEntity>

    @Query("SELECT * from time_table")
    fun getTimesFlow(): Flow<List<TimeEntity>>

    @Query("SELECT * from time_table WHERE time = :time")
    suspend fun getTime(time: String): TimeEntity?

    @Query("SELECT * from time_table WHERE id = :id")
    suspend fun getTime(id: Long): TimeEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTime(time: TimeEntity): Long

    @Update
    suspend fun updateTime(time: TimeEntity)

    @Delete
    suspend fun deleteTime(time: TimeEntity)

    @Query("DELETE from time_table WHERE id = :id")
    suspend fun deleteTime(id: Long)

    @Query("DELETE from time_table")
    suspend fun deleteAllTimes()
}