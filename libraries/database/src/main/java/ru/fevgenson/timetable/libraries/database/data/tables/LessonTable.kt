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
            entity = TeachersNameEntity::class,
            parentColumns = ["id"],
            childColumns = ["teachersName"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        ), ForeignKey(
            entity = EmailEntity::class,
            parentColumns = ["id"],
            childColumns = ["email"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        ), ForeignKey(
            entity = PhoneEntity::class,
            parentColumns = ["id"],
            childColumns = ["phone"],
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
    @ColumnInfo(index = true) val teachersName: Long? = null,
    @ColumnInfo(index = true) val email: Long? = null,
    @ColumnInfo(index = true) val phone: Long? = null
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

    @Query("DELETE from lesson_table")
    suspend fun deleteAllLessons()
}