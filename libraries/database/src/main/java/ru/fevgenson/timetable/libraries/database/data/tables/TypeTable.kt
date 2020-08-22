package ru.fevgenson.timetable.libraries.database.data.tables

import androidx.room.*

@Entity(tableName = "type_table", indices = [Index(value = ["type"], unique = true)])
data class TypeEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val type: String
)

@Dao
interface TypeDao {

    @Query("SELECT * from type_table")
    suspend fun getTypes(): List<TypeEntity>

    @Query("SELECT * from type_table WHERE type = :type")
    suspend fun getType(type: String): TypeEntity?

    @Query("SELECT * from type_table WHERE id = :id")
    suspend fun getType(id: Long): TypeEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertType(type: TypeEntity): Long

    @Update
    suspend fun updateType(type: TypeEntity)

    @Delete
    suspend fun deleteType(type: TypeEntity)

    @Query("DELETE from type_table")
    suspend fun deleteAllHTypes()
}