package ru.fevgenson.timetable.shared.lesson.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.fevgenson.timetable.shared.lesson.data.dto.LessonIdsDto


@Dao
interface LessonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLesson(lesson: LessonIdsDto): Long

    @Query("DELETE from lesson_table WHERE lesson_id = :id")
    suspend fun deleteLesson(id: Long)

    @Query("DELETE from lesson_table")
    suspend fun deleteAllLessons()
}