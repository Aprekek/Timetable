package ru.fevgenson.timetable.shared.lesson.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "classroom_table", indices = [Index(value = ["classroom"], unique = true)])
data class ClassroomDto(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "classroom_id")
    val id: Long = 0,
    @ColumnInfo(name = "classroom")
    val classroom: String
)