package ru.fevgenson.timetable.shared.lesson.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "housing_table", indices = [Index(value = ["housing"], unique = true)])
data class HousingDto(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "housing_id")
    val id: Long = 0,
    @ColumnInfo(name = "housing")
    val housing: String
)