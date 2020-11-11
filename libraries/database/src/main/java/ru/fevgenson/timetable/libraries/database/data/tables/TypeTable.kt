package ru.fevgenson.timetable.libraries.database.data.tables

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "type_table", indices = [Index(value = ["type"], unique = true)])
data class TypeEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val type: String
)

@Dao
internal interface TypeDao {

    @Query("SELECT * from type_table")
    suspend fun getTypes(): List<TypeEntity>

    @Query("SELECT * from type_table")
    fun getTypesFlow(): Flow<List<TypeEntity>>

    @Query("SELECT * from type_table WHERE type = :type")
    suspend fun getType(type: String): TypeEntity?

    @Query("SELECT * from type_table WHERE id = :id")
    suspend fun getType(id: Long): TypeEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertType(type: TypeEntity): Long

    @Update
    suspend fun updateType(type: TypeEntity)

    @Delete
    suspend fun deleteType(type: TypeEntity)

    @Query("DELETE from type_table WHERE id = :id")
    suspend fun deleteType(id: Long)

    @Query("DELETE from type_table")
    suspend fun deleteAllHTypes()
}