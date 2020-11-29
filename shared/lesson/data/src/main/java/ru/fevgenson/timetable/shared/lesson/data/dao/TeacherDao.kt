package ru.fevgenson.timetable.shared.lesson.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.fevgenson.timetable.shared.lesson.data.dto.TeacherDto

@Dao
interface TeacherDao {

    suspend fun getTeacherIdOrInsert(
        name: String,
        email: String?,
        phone: String?
    ): Long {
        val teacherDto = getTeacher(name)
        return when {
            teacherDto == null -> insertTeacher(
                TeacherDto(
                    name = name,
                    email = email,
                    phone = phone
                )
            )
            teacherDto.email != email || teacherDto.phone != phone -> insertTeacher(
                teacherDto.copy(
                    email = email,
                    phone = phone
                )
            )
            else -> teacherDto.id
        }
    }

    @Query("SELECT * from teacher_table")
    suspend fun getAllTeachers(): List<TeacherDto>

    @Query("SELECT * from teacher_table")
    fun getAllTeachersFlow(): Flow<List<TeacherDto>>

    @Query("SELECT * from teacher_table WHERE name = :name")
    suspend fun getTeacher(name: String): TeacherDto?

    @Query("SELECT * from teacher_table WHERE teacher_id = :id")
    suspend fun getTeacher(id: Long): TeacherDto

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeacher(teacher: TeacherDto): Long

    @Query("DELETE from teacher_table WHERE teacher_id = :id")
    suspend fun deleteTeacher(id: Long)

    @Query("DELETE from teacher_table")
    suspend fun deleteAllTeachers()
}