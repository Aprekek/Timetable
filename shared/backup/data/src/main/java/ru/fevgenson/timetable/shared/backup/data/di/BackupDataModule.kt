package ru.fevgenson.timetable.shared.backup.data.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.fevgenson.timetable.shared.backup.data.repository.BackupRepositoryImpl
import ru.fevgenson.timetable.shared.backup.domain.repository.BackupRepository
import ru.fevgenson.timetable.shared.lesson.data.database.LessonDatabase

val backupDataModule = module {
    factory<BackupRepository> {
        BackupRepositoryImpl(
            context = androidContext(),
            databaseName = LessonDatabase.DEVELOP_DB_NAME,
            localDatabase = get<LessonDatabase>()
        )
    }
}