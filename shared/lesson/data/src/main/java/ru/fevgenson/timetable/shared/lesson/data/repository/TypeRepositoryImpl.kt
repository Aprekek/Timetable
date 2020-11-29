package ru.fevgenson.timetable.shared.lesson.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import ru.fevgenson.timetable.shared.lesson.data.datasource.TypeDataSource
import ru.fevgenson.timetable.shared.lesson.data.mapper.toDto
import ru.fevgenson.timetable.shared.lesson.data.mapper.toEntity
import ru.fevgenson.timetable.shared.lesson.domain.entity.TypeEntity
import ru.fevgenson.timetable.shared.lesson.domain.repository.TypeRepository

class TypeRepositoryImpl(
    private val dataSource: TypeDataSource
) : TypeRepository {

    override suspend fun getType(id: Long): TypeEntity =
        withContext(Dispatchers.IO) {
            dataSource.getType(id).toEntity()
        }

    override suspend fun getAllTypes(): List<TypeEntity> =
        withContext(Dispatchers.IO) {
            dataSource.getAllTypes().map { it.toEntity() }
        }

    override fun getAllTypesFlow(): Flow<List<TypeEntity>> =
        dataSource.getAllTypesFlow()
            .map { list -> list.map { it.toEntity() } }
            .flowOn(Dispatchers.IO)

    override suspend fun saveType(typeEntity: TypeEntity) {
        withContext(Dispatchers.IO) {
            dataSource.saveType(typeEntity.toDto())
        }
    }

    override suspend fun deleteType(id: Long) {
        withContext(Dispatchers.IO) {
            dataSource.deleteType(id)
        }
    }

    override suspend fun deleteAllTypes() {
        withContext(Dispatchers.IO) {
            dataSource.deleteAllTypes()
        }
    }
}