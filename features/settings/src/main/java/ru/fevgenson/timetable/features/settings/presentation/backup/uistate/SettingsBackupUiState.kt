package ru.fevgenson.timetable.features.settings.presentation.backup.uistate

sealed class SettingsBackupUiState {

    object Loading : SettingsBackupUiState()
    object Content : SettingsBackupUiState()
}