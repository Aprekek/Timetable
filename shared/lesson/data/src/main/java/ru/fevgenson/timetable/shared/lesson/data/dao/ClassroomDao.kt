package ru.fevgenson.timetable.shared.lesson.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.fevgenson.timetable.shared.lesson.data.dto.ClassroomDto

@Dao
interface ClassroomDao {

    suspend fun getClassroomIdOrInsert(classroom: String): Long =
        getClassroom(classroom)?.id ?: insertClassroom(ClassroomDto(classroom = classroom))

    @Query("SELECT * from classroom_table")
    suspend fun getAllClassrooms(): List<ClassroomDto>

    @Query("SELECT * from classroom_table")
    fun getAllClassroomsFlow(): Flow<List<ClassroomDto>>

    @Query("SELECT * from classroom_table WHERE classroom = :classroom")
    suspend fun getClassroom(classroom: String): ClassroomDto?

    @Query("SELECT * from classroom_table WHERE classroom_id = :id")
    suspend fun getClassroom(id: Long): ClassroomDto

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClassroom(classroom: ClassroomDto): Long

    @Query("DELETE from classroom_table WHERE classroom_id = :id")
    suspend fun deleteClassroom(id: Long)

    @Query("DELETE from classroom_table")
    suspend fun deleteAllClassrooms()
}