package ru.fevgenson.timetable.features.settings.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.fevgenson.timetable.features.settings.presentation.SettingsViewModel
import ru.fevgenson.timetable.features.settings.presentation.backup.SettingsBackupViewModel
import ru.fevgenson.timetable.features.settings.presentation.notifications.SettingsNotificationsViewModel
import ru.fevgenson.timetable.features.settings.presentation.style.SettingsStyleViewModel

private val viewModelModule = module {
    viewModel { SettingsViewModel() }
    viewModel {
        SettingsStyleViewModel(
            getSavedThemeUseCase = get(),
            saveThemeUseCase = get()
        )
    }
    viewModel {
        SettingsNotificationsViewModel(
            getForegroundServiceEnabledUseCase = get(),
            saveForegroundServiceEnabledUseCase = get(),
            getTimeBaseNotificationsEnabledUseCase = get(),
            saveTimeBaseNotificationsEnabledUseCase = get()
        )
    }
    viewModel {
        SettingsBackupViewModel(
            createBackupUseCase = get(),
            restoreBackupUseCase = get()
        )
    }
}

val settingsModule = listOf(
    viewModelModule
)