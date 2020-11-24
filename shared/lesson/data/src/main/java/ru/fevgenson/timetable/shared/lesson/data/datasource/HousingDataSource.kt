package ru.fevgenson.timetable.shared.lesson.data.datasource

import kotlinx.coroutines.flow.Flow
import ru.fevgenson.timetable.shared.lesson.data.dto.HousingDto

interface HousingDataSource {

    suspend fun getHousing(id: Long): HousingDto
    suspend fun getAllHousings(): List<HousingDto>
    fun getAllHousingsFlow(): Flow<List<HousingDto>>
    suspend fun saveHousing(housingDto: HousingDto)
    suspend fun deleteHousing(id: Long)
    suspend fun deleteAllHousings()
}