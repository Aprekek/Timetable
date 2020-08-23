package ru.fevgenson.timetable.libraries.database.data.tables

import androidx.room.*

@Entity(
    tableName = "teachers_name_table",
    indices = [Index(value = ["teachersName"], unique = true)]
)
data class TeachersNameEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val teachersName: String
)

@Dao
interface TeachersNameDao {

    @Query("SELECT * from teachers_name_table")
    suspend fun getTeachersNames(): List<TeachersNameEntity>

    @Query("SELECT * from teachers_name_table WHERE teachersName = :teachersName")
    suspend fun getTeachersName(teachersName: String): TeachersNameEntity?

    @Query("SELECT * from teachers_name_table WHERE id = :id")
    suspend fun getTeachersName(id: Long): TeachersNameEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTeachersName(teachersName: TeachersNameEntity): Long

    @Update
    suspend fun updateTeachersName(teachersName: TeachersNameEntity)

    @Delete
    suspend fun deleteTeachersName(teachersName: TeachersNameEntity)

    @Query("DELETE from teachers_name_table WHERE id = :id")
    suspend fun deleteTeachersName(id: Long)

    @Query("DELETE from teachers_name_table")
    suspend fun deleteAllTeachersNames()
}