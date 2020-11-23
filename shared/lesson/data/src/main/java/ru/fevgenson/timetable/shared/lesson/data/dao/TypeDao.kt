package ru.fevgenson.timetable.shared.lesson.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.fevgenson.timetable.shared.lesson.data.dto.TypeDto

@Dao
interface TypeDao {

    suspend fun getTypeIdOrInsert(type: String): Long =
        getType(type)?.id ?: insertType(TypeDto(type = type))

    @Query("SELECT * from type_table")
    suspend fun getAllTypes(): List<TypeDto>

    @Query("SELECT * from type_table")
    fun getAllTypesFlow(): Flow<List<TypeDto>>

    @Query("SELECT * from type_table WHERE type = :type")
    suspend fun getType(type: String): TypeDto?

    @Query("SELECT * from type_table WHERE type_id = :id")
    suspend fun getType(id: Long): TypeDto

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertType(type: TypeDto): Long

    @Query("DELETE from type_table WHERE type_id = :id")
    suspend fun deleteType(id: Long)

    @Query("DELETE from type_table")
    suspend fun deleteAllTypes()
}