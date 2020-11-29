package ru.fevgenson.timetable.shared.lesson.data.datasource

import kotlinx.coroutines.flow.Flow
import ru.fevgenson.timetable.shared.lesson.data.dao.CommonLessonDao
import ru.fevgenson.timetable.shared.lesson.data.dto.HousingDto

class HousingDataSourceImpl(
    private val dao: CommonLessonDao
) : HousingDataSource {

    override suspend fun getHousing(id: Long): HousingDto = dao.getHousing(id)

    override suspend fun getAllHousings(): List<HousingDto> = dao.getAllHousings()

    override fun getAllHousingsFlow(): Flow<List<HousingDto>> = dao.getAllHousingsFlow()

    override suspend fun saveHousing(housingDto: HousingDto) {
        dao.insertHousing(housingDto)
    }

    override suspend fun deleteHousing(id: Long) {
        dao.deleteHousing(id)
    }

    override suspend fun deleteAllHousings() {
        dao.deleteAllHousings()
    }
}