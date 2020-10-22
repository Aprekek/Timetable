package ru.fevgenson.timetable.libraries.database.data.repository

import android.content.ContentResolver
import android.net.Uri
import androidx.room.RoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.fevgenson.timetable.libraries.database.domain.repository.BackupRepository
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

@Suppress("BlockingMethodInNonBlockingContext")
internal class BackupRepositoryImpl(
    private val localDatabase: RoomDatabase,
    private val localDatabasePath: String,
    private val contentResolver: ContentResolver
) : BackupRepository {

    override suspend fun createBackup(uri: Uri) {
        withContext(Dispatchers.IO) {
            localDatabase.close()
            val localDataBaseFile = File(localDatabasePath)
            val localDatabaseFileInputStream = FileInputStream(localDataBaseFile)
            val backupParcelFileDescriptor = contentResolver.openFileDescriptor(uri, "w")
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

    override suspend fun restoreBackup(uri: Uri) {
        withContext(Dispatchers.IO) {
            localDatabase.close()
            val localDataBaseFile = File(localDatabasePath)
            val localDatabaseFileOutputStream = FileOutputStream(localDataBaseFile)
            val backupParcelFileDescriptor = contentResolver.openFileDescriptor(uri, "r")
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