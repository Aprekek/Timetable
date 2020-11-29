package ru.fevgenson.timetable.shared.lesson.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.fevgenson.timetable.shared.lesson.data.dto.HousingDto

@Dao
interface HousingDao {

    suspend fun getHousingIdOrInsert(housing: String): Long =
        getHousing(housing)?.id ?: insertHousing(HousingDto(housing = housing))

    @Query("SELECT * from housing_table")
    suspend fun getAllHousings(): List<HousingDto>

    @Query("SELECT * from housing_table")
    fun getAllHousingsFlow(): Flow<List<HousingDto>>

    @Query("SELECT * from housing_table WHERE housing = :housing")
    suspend fun getHousing(housing: String): HousingDto?

    @Query("SELECT * from housing_table WHERE housing_id = :id")
    suspend fun getHousing(id: Long): HousingDto

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHousing(housing: HousingDto): Long

    @Query("DELETE from housing_table WHERE housing_id = :id")
    suspend fun deleteHousing(id: Long)

    @Query("DELETE from housing_table")
    suspend fun deleteAllHousings()
}