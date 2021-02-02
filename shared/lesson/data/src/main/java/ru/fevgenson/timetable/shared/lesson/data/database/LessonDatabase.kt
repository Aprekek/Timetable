package ru.fevgenson.timetable.shared.lesson.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.fevgenson.timetable.shared.lesson.data.dao.CommonLessonDao
import ru.fevgenson.timetable.shared.lesson.data.dto.*

@Database(
    entities = [
        LessonIdsDto::class,
        SubjectDto::class,
        HousingDto::class,
        ClassroomDto::class,
        TypeDto::class,
        TeacherDto::class,
        TimeDto::class
    ],
    version = 1,
    exportSchema = false
)
abstract class LessonDatabase : RoomDatabase() {

    companion object {
        const val DEVELOP_DB_NAME = "DEVELOP_DB_NAME_NEW"
    }

    abstract fun lessonDao(): CommonLessonDao
}