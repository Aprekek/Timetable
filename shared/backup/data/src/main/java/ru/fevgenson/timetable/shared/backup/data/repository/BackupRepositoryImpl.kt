package ru.fevgenson.timetable.shared.backup.data.repository

import android.content.Context
import android.net.Uri
import androidx.room.RoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.fevgenson.timetable.shared.backup.domain.repository.BackupRepository
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

@Suppress("BlockingMethodInNonBlockingContext")
class BackupRepositoryImpl(
    private val localDatabase: RoomDatabase,
    private val databaseName: String,
    private val context: Context
) : BackupRepository {

    override suspend fun createBackup(uri: String) {
        withContext(Dispatchers.IO) {
            localDatabase.close()
            val databasePath = context.getDatabasePath(databaseName).absolutePath
            val localDataBaseFile = File(databasePath)
            val localDatabaseFileInputStream = FileInputStream(localDataBaseFile)
            val contentResolver = context.contentResolver
            val backupParcelFileDescriptor = contentResolver.openFileDescriptor(Uri.parse(uri), "w")
            val backupFileOutputStream =
                FileOutputStream(backupParcelFileDescriptor?.fileDescriptor)
            try {
                val buf = ByteArray(localDatabaseFileInputStream.available())
                localDatabaseFileInputStream.read(buf)
                backupFileOutputStream.write(buf)
            } finally {
                localDatabaseFileInputStream.close()
                backupFileOutputStream.close()
            }
        }
    }

    override suspend fun restoreBackup(uri: String) {
        withContext(Dispatchers.IO) {
            localDatabase.close()
            val databasePath = context.getDatabasePath(databaseName).absolutePath
            val localDataBaseFile = File(databasePath)
            val localDatabaseFileOutputStream = FileOutputStream(localDataBaseFile)
            val contentResolver = context.contentResolver
            val backupParcelFileDescriptor = contentResolver.openFileDescriptor(Uri.parse(uri), "r")
            val backupFileInputStream = FileInputStream(backupParcelFileDescriptor?.fileDescriptor)
            try {
                val buf = ByteArray(backupFileInputStream.available())
                backupFileInputStream.read(buf)
                localDatabaseFileOutputStream.write(buf)
            } finally {
                localDatabaseFileOutputStream.close()
                backupFileInputStream.close()
            }
        }
    }
}