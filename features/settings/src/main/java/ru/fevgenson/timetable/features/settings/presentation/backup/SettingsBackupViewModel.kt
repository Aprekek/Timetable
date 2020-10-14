package ru.fevgenson.timetable.features.settings.presentation.backup

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.fevgenson.timetable.features.settings.R
import ru.fevgenson.timetable.features.settings.presentation.backup.uistate.SettingsBackupUiState
import ru.fevgenson.timetable.libraries.core.presentation.utils.eventutils.EventsDispatcher
import ru.fevgenson.timetable.libraries.database.domain.usecase.CreateBackupUseCase
import ru.fevgenson.timetable.libraries.database.domain.usecase.RestoreBackupUseCase
import java.io.IOException
import java.util.*

class SettingsBackupViewModel(
    private val createBackupUseCase: CreateBackupUseCase,
    private val restoreBackupUseCase: RestoreBackupUseCase
) : ViewModel() {

    private companion object {

        const val BACKUP = "Backup"
        const val BACKUP_EXTENSION = ".bin"
    }

    interface EventListener {

        fun navigateBack()
        fun openBackupFile()
        fun createBackupFile(backupFileName: String)
        fun recreate()
        fun showToast(textRes: Int)
    }

    val eventsDispatcher = EventsDispatcher<EventListener>()

    private val _uiStateLiveData =
        MutableLiveData<SettingsBackupUiState>(SettingsBackupUiState.Content)
    val uiStateLiveData: LiveData<SettingsBackupUiState>
        get() = _uiStateLiveData

    fun onBackButtonPressed() {
        eventsDispatcher.dispatchEvent { navigateBack() }
    }

    fun onCreateBackupClick() {
        val backupFileName = "$BACKUP ${Date()}$BACKUP_EXTENSION"
        eventsDispatcher.dispatchEvent { createBackupFile(backupFileName) }
    }

    fun onRestoreBackupClick() {
        eventsDispatcher.dispatchEvent { openBackupFile() }
    }

    fun setCreatedFile(uri: Uri) {
        viewModelScope.launch {
            _uiStateLiveData.value = SettingsBackupUiState.Loading
            try {
                createBackupUseCase(uri)
                eventsDispatcher.dispatchEvent {
                    showToast(R.string.settings_toast_backup_create_success)
                }
            } catch (e: IOException) {
                eventsDispatcher.dispatchEvent {
                    showToast(R.string.settings_toast_backup_create_error)
                }
            }
            _uiStateLiveData.value = SettingsBackupUiState.Content
            eventsDispatcher.dispatchEvent { recreate() }
        }
    }

    fun setOpenedFile(uri: Uri) {
        viewModelScope.launch {
            _uiStateLiveData.value = SettingsBackupUiState.Loading
            try {
                restoreBackupUseCase(uri)
                eventsDispatcher.dispatchEvent {
                    showToast(R.string.settings_toast_backup_restore_success)
                }
            } catch (e: IOException) {
                eventsDispatcher.dispatchEvent {
                    showToast(R.string.settings_toast_backup_restore_error)
                }
            }
            _uiStateLiveData.value = SettingsBackupUiState.Content
            eventsDispatcher.dispatchEvent { recreate() }
        }
    }
}