package ru.fevgenson.timetable.libraries.database.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.fevgenson.timetable.libraries.database.data.dao.DaoImplementations
import ru.fevgenson.timetable.libraries.database.data.entities.*

@Database(
    entities = [
        LessonEntity::class,
        SubjectEntity::class,
        HousingEntity::class,
        ClassroomEntity::class,
        TypeEntity::class,
        TeachersNameEntity::class,
        EmailEntity::class,
        PhoneEntity::class,
        TimeEntity::class,
        DayEntity::class,
        WeekTypeEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class LessonDatabase : RoomDatabase() {

    //    abstract fun lessonDao(): LessonDao
    abstract fun lessonDao(): DaoImplementations
}