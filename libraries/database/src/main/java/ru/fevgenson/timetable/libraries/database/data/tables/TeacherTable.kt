package ru.fevgenson.timetable.libraries.database.data.tables

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.fevgenson.timetable.shared.lesson.domain.entities.DomainTeacherEntity

@Entity(
    tableName = "teacher_table",
    indices = [Index(value = ["name"], unique = true)]
)
data class TeacherEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val phone: String?,
    val email: String?
)

fun TeacherEntity.toDomainTeacherEntity() = DomainTeacherEntity(name, phone, email)

@Dao
internal interface TeachersNameDao {

    @Query("SELECT * from teacher_table")
    suspend fun getTeachers(): List<TeacherEntity>

    @Query("SELECT * from teacher_table")
    fun getTeachersFlow(): Flow<List<TeacherEntity>>

    @Query("SELECT * from teacher_table WHERE name = :teachersName")
    suspend fun getTeacher(teachersName: String): TeacherEntity?

    @Query("SELECT * from teacher_table WHERE id = :id")
    suspend fun getTeacher(id: Long): TeacherEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTeacher(teacher: TeacherEntity): Long

    @Update
    suspend fun updateTeacher(teacher: TeacherEntity)

    @Delete
    suspend fun deleteTeacher(teacher: TeacherEntity)

    @Query("DELETE from teacher_table WHERE id = :id")
    suspend fun deleteTeacher(id: Long)

    @Query("DELETE from teacher_table")
    suspend fun deleteAllTeachers()
}