package ru.fevgenson.timetable.libraries.database.domain.usecase

import android.net.Uri
import ru.fevgenson.timetable.libraries.database.domain.repository.BackupRepository

class RestoreBackupUseCase(
    private val repository: BackupRepository
) {

    suspend operator fun invoke(uri: Uri) {
        repository.restoreBackup(uri)
    }
}