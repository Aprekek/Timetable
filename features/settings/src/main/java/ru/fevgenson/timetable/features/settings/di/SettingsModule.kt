package ru.fevgenson.timetable.features.settings.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.fevgenson.timetable.features.settings.presentation.SettingsViewModel

private val viewModelModule = module {
    viewModel { SettingsViewModel() }
}

val settingsModule = listOf(
    viewModelModule
)