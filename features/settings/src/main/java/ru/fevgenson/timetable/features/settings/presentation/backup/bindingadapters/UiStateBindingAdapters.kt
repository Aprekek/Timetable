package ru.fevgenson.timetable.features.settings.presentation.backup.bindingadapters

import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import ru.fevgenson.timetable.features.settings.presentation.backup.uistate.SettingsBackupUiState

@BindingAdapter("backupUiState")
fun LinearLayout.setBackupUiState(uiState: SettingsBackupUiState) {
    isVisible = uiState is SettingsBackupUiState.Content
}

@BindingAdapter("backupUiState")
fun ProgressBar.setBackupUiState(uiState: SettingsBackupUiState) {
    isVisible = uiState is SettingsBackupUiState.Loading
}