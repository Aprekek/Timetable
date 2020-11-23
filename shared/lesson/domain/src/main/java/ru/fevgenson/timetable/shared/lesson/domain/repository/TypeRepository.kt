package ru.fevgenson.timetable.shared.lesson.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.fevgenson.timetable.shared.lesson.domain.entity.TypeEntity

interface TypeRepository {

    suspend fun getType(id: Long): TypeEntity
    suspend fun getAllTypes(): List<TypeEntity>
    fun getAllTypesFlow(): Flow<List<TypeEntity>>
    suspend fun saveType(typeEntity: TypeEntity)
    suspend fun deleteType(id: Long)
    suspend fun deleteAllTypes()
}