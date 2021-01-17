package ru.fevgenson.timetable.shared.settings.data.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.fevgenson.timetable.shared.settings.data.repository.SettingsRepositoryImpl
import ru.fevgenson.timetable.shared.settings.domain.repository.SettingsRepository

val settingsDataModule = module {
    single<SettingsRepository> {
        SettingsRepositoryImpl(
            sharedPreferences = SettingsRepositoryImpl.getSharedPreferences(androidContext())
        )
    }
}