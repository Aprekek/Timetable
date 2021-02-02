package ru.fevgenson.timetable.shared.backup.domain.usecase

import ru.fevgenson.timetable.shared.backup.domain.repository.BackupRepository

class CreateBackupUseCase(
    private val backupRepository: BackupRepository
) {

    suspend operator fun invoke(uri: String) {
        backupRepository.createBackup(uri)
    }
}