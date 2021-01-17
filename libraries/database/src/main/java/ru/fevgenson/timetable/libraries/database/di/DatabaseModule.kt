package ru.fevgenson.timetable.libraries.database.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.fevgenson.timetable.libraries.database.data.database.LessonDatabase
import ru.fevgenson.timetable.libraries.database.data.datasource.FieldsDataSource
import ru.fevgenson.timetable.libraries.database.data.datasource.FieldsDataSourceImpl
import ru.fevgenson.timetable.libraries.database.data.datasource.LessonDataSource
import ru.fevgenson.timetable.libraries.database.data.datasource.LessonDataSourceImpl
import ru.fevgenson.timetable.libraries.database.data.repository.BackupRepositoryImpl
import ru.fevgenson.timetable.libraries.database.data.repository.FieldsRepositoryImpl
import ru.fevgenson.timetable.libraries.database.data.repository.LessonRepositoryImpl
import ru.fevgenson.timetable.libraries.database.domain.repository.BackupRepository
import ru.fevgenson.timetable.libraries.database.domain.repository.FieldsRepository
import ru.fevgenson.timetable.libraries.database.domain.repository.LessonRepository
import ru.fevgenson.timetable.libraries.database.domain.usecase.CreateBackupUseCase
import ru.fevgenson.timetable.libraries.database.domain.usecase.RestoreBackupUseCase

private val daoModule = module {
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
}

private val dataSourceModule = module {
    single<LessonDataSource> { LessonDataSourceImpl(get()) }
    single<FieldsDataSource> { FieldsDataSourceImpl(get()) }
}

private val repositoryModule = module {
    factory<LessonRepository> { LessonRepositoryImpl(get()) }
    factory<FieldsRepository> { FieldsRepositoryImpl(get()) }
    factory<BackupRepository> {
        BackupRepositoryImpl(
            localDatabase = get<LessonDatabase>(),
            localDatabasePath = androidContext().getDatabasePath(LessonDatabase.DEVELOP_DB_NAME).absolutePath,
            contentResolver = androidContext().contentResolver
        )
    }
}

private val useCaseModule = module {
    factory { CreateBackupUseCase(get()) }
    factory { RestoreBackupUseCase(get()) }
}

val databaseModule = listOf(
    daoModule,
    dataSourceModule,
    repositoryModule,
    useCaseModule
)