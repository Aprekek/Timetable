package ru.fevgenson.timetable.shared.backup.domain.di

import org.koin.dsl.module
import ru.fevgenson.timetable.shared.backup.domain.usecase.CreateBackupUseCase
import ru.fevgenson.timetable.shared.backup.domain.usecase.RestoreBackupUseCase

val backupDomainModule = module {
    factory { CreateBackupUseCase(get()) }
    factory { RestoreBackupUseCase(get()) }
}