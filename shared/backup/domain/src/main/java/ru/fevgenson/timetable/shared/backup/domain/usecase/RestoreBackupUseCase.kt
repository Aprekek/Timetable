package ru.fevgenson.timetable.shared.backup.domain.usecase

import ru.fevgenson.timetable.shared.backup.domain.repository.BackupRepository

class RestoreBackupUseCase(
    private val repository: BackupRepository
) {

    suspend operator fun invoke(uri: String) {
        repository.restoreBackup(uri)
    }
}