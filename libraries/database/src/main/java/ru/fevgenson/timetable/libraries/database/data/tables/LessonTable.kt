package ru.fevgenson.timetable.libraries.database.data.tables

import androidx.room.*

@Entity(
    tableName = "lesson_table",
    foreignKeys = [
        ForeignKey(
            entity = SubjectEntity::class,
            parentColumns = ["id"],
            childColumns = ["subject"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        ), ForeignKey(
            entity = HousingEntity::class,
            parentColumns = ["id"],
            childColumns = ["housing"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        ), ForeignKey(
            entity = ClassroomEntity::class,
            parentColumns = ["id"],
            childColumns = ["classroom"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        ), ForeignKey(
            entity = TypeEntity::class,
            parentColumns = ["id"],
            childColumns = ["type"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        ), ForeignKey(
            entity = TeacherEntity::class,
            parentColumns = ["id"],
            childColumns = ["teacher"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        ), ForeignKey(
            entity = TimeEntity::class,
            parentColumns = ["id"],
            childColumns = ["time"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        )
    ]
)
data class LessonEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(index = true) val subject: Long = 0,
    @ColumnInfo(index = true) val time: Long = 0,
    @ColumnInfo(index = true) val day: Int = 0,
    @ColumnInfo(index = true) val weekType: Int = 0,
    @ColumnInfo(index = true) val housing: Long? = null,
    @ColumnInfo(index = true) val classroom: Long? = null,
    @ColumnInfo(index = true) val type: Long? = null,
    @ColumnInfo(index = true) val teacher: Long? = null
)

@Dao
interface LessonDao {

    @Query("SELECT * from lesson_table WHERE id = :id")
    suspend fun getLesson(id: Long): LessonEntity

    @Insert
    suspend fun insertLesson(lesson: LessonEntity): Long

    @Update
    suspend fun updateLesson(lesson: LessonEntity)

    @Delete
    suspend fun deleteLesson(lesson: LessonEntity)

    @Query("DELETE from lesson_table WHERE id = :id")
    suspend fun deleteLesson(id: Long)

    @Query("DELETE from lesson_table")
    suspend fun deleteAllLessons()
}