package ru.fevgenson.timetable.shared.backup.domain.repository

interface BackupRepository {
    suspend fun createBackup(uri: String)
    suspend fun restoreBackup(uri: String)
}