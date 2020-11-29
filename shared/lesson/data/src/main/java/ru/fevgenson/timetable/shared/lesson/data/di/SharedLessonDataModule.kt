package ru.fevgenson.timetable.shared.lesson.data.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.fevgenson.timetable.shared.lesson.data.database.LessonDatabase
import ru.fevgenson.timetable.shared.lesson.data.datasource.*
import ru.fevgenson.timetable.shared.lesson.data.repository.*
import ru.fevgenson.timetable.shared.lesson.domain.repository.*

val sharedLessonDataModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            LessonDatabase::class.java,
            LessonDatabase.DEVELOP_DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    factory { get<LessonDatabase>().lessonDao() }

    factory<ClassroomDataSource> { ClassroomDataSourceImpl(get()) }
    factory<HousingDataSource> { HousingDataSourceImpl(get()) }
    factory<LessonDataSource> { LessonDataSourceImpl(get()) }
    factory<SubjectDataSource> { SubjectDataSourceImpl(get()) }
    factory<TeacherDataSource> { TeacherDataSourceImpl(get()) }
    factory<TimeDataSource> { TimeDataSourceImpl(get()) }
    factory<TypeDataSource> { TypeDataSourceImpl(get()) }

    factory<ClassroomRepository> { ClassroomRepositoryImpl(get()) }
    factory<HousingRepository> { HousingRepositoryImpl(get()) }
    factory<LessonRepository> { LessonRepositoryImpl(get()) }
    factory<SubjectRepository> { SubjectRepositoryImpl(get()) }
    factory<TeacherRepository> { TeacherRepositoryImpl(get()) }
    factory<TimeRepository> { TimeRepositoryImpl(get()) }
    factory<TypeRepository> { TypeRepositoryImpl(get()) }
}