package ru.fevgenson.timetable.libraries.database.data.tables

import androidx.room.*

@Entity(
    tableName = "email_table",
    indices = [androidx.room.Index(value = ["email"], unique = true)]
)
data class EmailEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val email: String
)

@Dao
interface EmailDao {

    @Query("SELECT * from email_table")
    suspend fun getEmails(): List<EmailEntity>

    @Query("SELECT * from email_table WHERE email = :email")
    suspend fun getEmail(email: String): EmailEntity?

    @Query("SELECT * from email_table WHERE id = :id")
    suspend fun getEmail(id: Long): EmailEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertEmail(email: EmailEntity): Long

    @Update
    suspend fun updateEmail(email: EmailEntity)

    @Delete
    suspend fun deleteEmail(email: EmailEntity)

    @Query("DELETE from email_table")
    suspend fun deleteAllEmails()
}