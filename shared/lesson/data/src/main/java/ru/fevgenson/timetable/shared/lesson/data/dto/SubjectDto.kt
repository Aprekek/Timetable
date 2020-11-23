package ru.fevgenson.timetable.shared.lesson.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "subject_table", indices = [Index(value = ["subject"], unique = true)])
data class SubjectDto(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "subject_id")
    val id: Long = 0,
    @ColumnInfo(name = "subject")
    val subject: String
)