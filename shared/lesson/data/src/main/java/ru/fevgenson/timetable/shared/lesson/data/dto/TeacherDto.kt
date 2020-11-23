package ru.fevgenson.timetable.shared.lesson.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "teacher_table", indices = [Index(value = ["name"], unique = true)])
data class TeacherDto(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "teacher_id")
    val id: Long = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "phone")
    val phone: String? = null,
    @ColumnInfo(name = "email")
    val email: String? = null
)