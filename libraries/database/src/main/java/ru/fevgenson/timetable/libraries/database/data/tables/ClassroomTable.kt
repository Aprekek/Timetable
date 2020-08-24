package ru.fevgenson.timetable.libraries.database.data.tables

import androidx.room.*

@Entity(tableName = "classroom_table", indices = [Index(value = ["classroom"], unique = true)])
data class ClassroomEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val classroom: String
)

@Dao
internal interface ClassroomDao {

    @Query("SELECT * from classroom_table")
    suspend fun getClassrooms(): List<ClassroomEntity>

    @Query("SELECT * from classroom_table WHERE classroom = :classroom")
    suspend fun getClassroom(classroom: String): ClassroomEntity?

    @Query("SELECT * from classroom_table WHERE id = :id")
    suspend fun getClassroom(id: Long): ClassroomEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertClassroom(classroom: ClassroomEntity): Long

    @Update
    suspend fun updateClassroom(classroom: ClassroomEntity)

    @Delete
    suspend fun deleteClassroom(classroom: ClassroomEntity)

    @Query("DELETE from classroom_table WHERE id = :id")
    suspend fun deleteClassroom(id: Long)

    @Query("DELETE from classroom_table")
    suspend fun deleteAllClassrooms()
}