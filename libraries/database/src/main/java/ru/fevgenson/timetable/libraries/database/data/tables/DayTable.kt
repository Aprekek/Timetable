package ru.fevgenson.timetable.libraries.database.data.tables

import androidx.room.*

@Entity(tableName = "day_table", indices = [Index(value = ["day"], unique = true)])
data class DayEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val day: Int
)

@Dao
interface DayDao {

    @Query("SELECT * from day_table")
    suspend fun getDays(): List<DayEntity>

    @Query("SELECT * from day_table WHERE day = :day")
    suspend fun getDay(day: Int): DayEntity?

    @Query("SELECT * from day_table WHERE id = :id")
    suspend fun getDay(id: Long): DayEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDay(day: DayEntity): Long

    @Update
    suspend fun updateDay(day: DayEntity)

    @Delete
    suspend fun deleteDay(day: DayEntity)

    @Query("DELETE from day_table")
    suspend fun deleteAllDays()
}