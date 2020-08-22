package ru.fevgenson.timetable.libraries.database.data.tables

import androidx.room.*

@Entity(tableName = "week_type_table", indices = [Index(value = ["weekType"], unique = true)])
data class WeekTypeEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val weekType: Int
)

@Dao
interface WeekTypeDao {

    @Query("SELECT * from week_type_table")
    suspend fun getWeekTypes(): List<WeekTypeEntity>

    @Query("SELECT * from week_type_table WHERE weekType = :weekType")
    suspend fun getWeekType(weekType: Int): WeekTypeEntity?

    @Query("SELECT * from week_type_table WHERE id = :id")
    suspend fun getWeekType(id: Long): WeekTypeEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeekType(weekType: WeekTypeEntity): Long

    @Update
    suspend fun updateWeekType(weekType: WeekTypeEntity)

    @Delete
    suspend fun deleteWeekType(weekType: WeekTypeEntity)
}