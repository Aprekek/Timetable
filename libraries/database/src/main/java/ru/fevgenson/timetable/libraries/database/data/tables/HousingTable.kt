package ru.fevgenson.timetable.libraries.database.data.tables

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "housing_table", indices = [Index(value = ["housing"], unique = true)])
data class HousingEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val housing: String
)

@Dao
internal interface HousingDao {

    @Query("SELECT * from housing_table")
    suspend fun getHousings(): List<HousingEntity>

    @Query("SELECT * from housing_table")
    fun getHousingsFlow(): Flow<List<HousingEntity>>

    @Query("SELECT * from housing_table WHERE housing = :housing")
    suspend fun getHousing(housing: String): HousingEntity?

    @Query("SELECT * from housing_table WHERE id = :id")
    suspend fun getHousing(id: Long): HousingEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHousing(housing: HousingEntity): Long

    @Update
    suspend fun updateHousing(housing: HousingEntity)

    @Delete
    suspend fun deleteHousing(housing: HousingEntity)

    @Query("DELETE from housing_table WHERE id = :id")
    suspend fun deleteHousing(id: Long)

    @Query("DELETE from housing_table")
    suspend fun deleteAllHousings()
}