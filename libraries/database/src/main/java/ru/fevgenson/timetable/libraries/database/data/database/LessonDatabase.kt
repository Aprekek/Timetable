package ru.fevgenson.timetable.libraries.database.data.database

//import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import ru.fevgenson.timetable.libraries.database.data.entities.Lesson
//
//@Database(entities = [Lesson::class], version = 1, exportSchema = false)
//abstract class LessonDatabase : RoomDatabase() {
//    abstract fun lesson(): Lesson
//
//    companion object {
//        @Volatile
//        private var instance: LessonDatabase? = null
//
//        fun getDatabase(context: Context): LessonDatabase {
//            instance?.let { return it }
//            synchronized(this) {
//                instance =
//                    Room.databaseBuilder(context, LessonDatabase::class.java, "lesson_database")
//                        .build()
//                return instance as LessonDatabase
//            }
//        }
//    }
//}