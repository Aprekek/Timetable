package ru.fevgenson.timetable.shared.lesson.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "time_table", indices = [Index(value = ["time"], unique = true)])
data class TimeDto(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "time_id")
    val id: Long = 0,
    @ColumnInfo(name = "time")
    val time: String
)