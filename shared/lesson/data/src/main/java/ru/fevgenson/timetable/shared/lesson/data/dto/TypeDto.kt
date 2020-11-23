package ru.fevgenson.timetable.shared.lesson.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "type_table", indices = [Index(value = ["type"], unique = true)])
data class TypeDto(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "type_id")
    val id: Long = 0,
    @ColumnInfo(name = "type")
    val type: String
)