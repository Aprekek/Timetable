package ru.fevgenson.timetable.features.settings.presentation.backup

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.fevgenson.timetable.libraries.core.presentation.utils.eventutils.EventsDispatcher
import ru.fevgenson.timetable.libraries.database.domain.usecase.CreateBackupUseCase
import ru.fevgenson.timetable.libraries.database.domain.usecase.RestoreBackupUseCase
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
    }

    val eventsDispatcher = EventsDispatcher<EventListener>()

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
            createBackupUseCase(uri)
        }
    }

    fun setOpenedFile(uri: Uri) {
        viewModelScope.launch {
            restoreBackupUseCase(uri)
        }
    }
}