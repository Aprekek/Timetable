package ru.fevgenson.timetable.shared.lesson.data.datasource

import kotlinx.coroutines.flow.Flow
import ru.fevgenson.timetable.shared.lesson.data.dto.TypeDto

interface TypeDataSource {

    suspend fun getType(id: Long): TypeDto
    suspend fun getAllTypes(): List<TypeDto>
    fun getAllTypesFlow(): Flow<List<TypeDto>>
    suspend fun saveType(typeDto: TypeDto)
    suspend fun deleteType(id: Long)
    suspend fun deleteAllTypes()
}