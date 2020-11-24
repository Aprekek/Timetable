package ru.fevgenson.timetable.shared.lesson.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import ru.fevgenson.timetable.shared.lesson.data.datasource.HousingDataSource
import ru.fevgenson.timetable.shared.lesson.data.mapper.toDto
import ru.fevgenson.timetable.shared.lesson.data.mapper.toEntity
import ru.fevgenson.timetable.shared.lesson.domain.entity.HousingEntity
import ru.fevgenson.timetable.shared.lesson.domain.repository.HousingRepository

class HousingRepositoryImpl(
    private val dataSource: HousingDataSource
) : HousingRepository {

    override suspend fun getHousing(id: Long): HousingEntity =
        withContext(Dispatchers.IO) {
            dataSource.getHousing(id).toEntity()
        }

    override suspend fun getAllHousings(): List<HousingEntity> =
        withContext(Dispatchers.IO) {
            dataSource.getAllHousings().map { it.toEntity() }
        }

    override fun getAllHousingsFlow(): Flow<List<HousingEntity>> =
        dataSource.getAllHousingsFlow()
            .map { list -> list.map { it.toEntity() } }
            .flowOn(Dispatchers.IO)

    override suspend fun saveHousing(housingEntity: HousingEntity) {
        withContext(Dispatchers.IO) {
            dataSource.saveHousing(housingEntity.toDto())
        }
    }

    override suspend fun deleteHousing(id: Long) {
        withContext(Dispatchers.IO) {
            dataSource.deleteHousing(id)
        }
    }

    override suspend fun deleteAllHousings() {
        withContext(Dispatchers.IO) {
            dataSource.deleteAllHousings()
        }
    }
}