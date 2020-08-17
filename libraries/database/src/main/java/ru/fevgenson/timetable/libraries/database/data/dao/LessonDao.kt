package ru.fevgenson.timetable.libraries.database.data.dao

//import androidx.lifecycle.LiveData
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.Query
//import androidx.room.Update
//import ru.fevgenson.timetable.libraries.database.data.entities.Lesson
//
//@Dao
//interface LessonDao {
//
//    @Insert
//    suspend fun insertLesson(lesson: Lesson)
//
//    @Update
//    suspend fun updateLesson(lesson: Lesson)
//
//    @Query("SELECT * from lesson_table WHERE id = :id")
//    suspend fun getLesson(id: Int): Lesson
//
//    @Query("SELECT * from lesson_table WHERE day = :day AND weekType = :week")
//    fun getLessonsForDay(day: Int, week: Int): LiveData<List<Lesson>>
//
//    @Query("DELETE from lesson_table WHERE id = :id")
//    fun deleteLesson(id: Int)
//
//    @Query("DELETE from lesson_table")
//    suspend fun deleteAllLessons()
//}