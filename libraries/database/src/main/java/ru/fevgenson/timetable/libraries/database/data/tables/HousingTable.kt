package ru.fevgenson.timetable.libraries.database.data.tables

import androidx.room.*

@Entity(tableName = "housing_table", indices = [Index(value = ["housing"], unique = true)])
data class HousingEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val housing: String
)

@Dao
interface HousingDao {

    @Query("SELECT * from housing_table")
    suspend fun getHousings(): List<HousingEntity>

    @Query("SELECT * from housing_table WHERE housing = :housing")
    suspend fun getHousing(housing: String): HousingEntity?

    @Query("SELECT * from housing_table WHERE id = :id")
    suspend fun getHousing(id: Long): HousingEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHousing(housing: HousingEntity): Long

    @Update
    suspend fun updateHousing(housing: HousingEntity)

    @Delete
    suspend fun deleteHousing(housing: HousingEntity)

    @Query("DELETE from housing_table")
    suspend fun deleteAllHousings()
}