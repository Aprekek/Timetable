package ru.fevgenson.timetable.shared.lesson.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.fevgenson.timetable.shared.lesson.domain.entity.HousingEntity

interface HousingRepository {

    suspend fun getHousing(id: Long): HousingEntity
    suspend fun getAllHousings(): List<HousingEntity>
    fun getAllHousingsFlow(): Flow<List<HousingEntity>>
    suspend fun saveHousing(housingEntity: HousingEntity)
    suspend fun deleteHousing(id: Long)
    suspend fun deleteAllHousings()
}