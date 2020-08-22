package ru.fevgenson.timetable.libraries.database.data.tables

import androidx.room.*

@Entity(tableName = "phone_table", indices = [Index(value = ["phone"], unique = true)])
data class PhoneEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val phone: String
)

@Dao
interface PhoneDao {

    @Query("SELECT * from phone_table")
    suspend fun getPhones(): List<PhoneEntity>

    @Query("SELECT * from phone_table WHERE phone = :phone")
    suspend fun getPhone(phone: String): PhoneEntity?

    @Query("SELECT * from phone_table WHERE id = :id")
    suspend fun getPhone(id: Long): PhoneEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhone(phone: PhoneEntity): Long

    @Update
    suspend fun updatePhone(phone: PhoneEntity)

    @Delete
    suspend fun deletePhone(phone: PhoneEntity)
}