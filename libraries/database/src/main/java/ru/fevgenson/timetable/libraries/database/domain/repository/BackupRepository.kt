package ru.fevgenson.timetable.libraries.database.domain.repository

import android.net.Uri

interface BackupRepository {
    suspend fun createBackup(uri: Uri)
    suspend fun restoreBackup(uri: Uri)
}