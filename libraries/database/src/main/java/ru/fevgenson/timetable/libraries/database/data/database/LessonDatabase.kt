package ru.fevgenson.timetable.libraries.database.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.fevgenson.timetable.libraries.database.data.generalDao.GeneralDao
import ru.fevgenson.timetable.libraries.database.data.tables.*

@Database(
    entities = [
        LessonEntity::class,
        SubjectEntity::class,
        HousingEntity::class,
        ClassroomEntity::class,
        TypeEntity::class,
        TeacherEntity::class,
        TimeEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class LessonDatabase : RoomDatabase() {

    abstract fun lessonDao(): GeneralDao
}