package ru.fevgenson.timetable.libraries.database.data.tables

import androidx.room.*

@Entity(tableName = "time_table", indices = [Index(value = ["time"], unique = true)])
data class TimeEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val time: String
)

@Dao
interface TimeDao {

    @Query("SELECT * from time_table")
    suspend fun getTimes(): List<TimeEntity>

    @Query("SELECT * from time_table WHERE time = :time")
    suspend fun getTime(time: String): TimeEntity?

    @Query("SELECT * from time_table WHERE id = :id")
    suspend fun getTime(id: Long): TimeEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTime(time: TimeEntity): Long

    @Update
    suspend fun updateTime(time: TimeEntity)

    @Delete
    suspend fun deleteTime(time: TimeEntity)
}