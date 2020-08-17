package ru.fevgenson.timetable.libraries.database.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "lesson_table",
    foreignKeys = [
        ForeignKey(
            entity = SubjectEntity::class,
            parentColumns = ["id"],
            childColumns = ["subject"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        ), ForeignKey(
            entity = HousingEntity::class,
            parentColumns = ["id"],
            childColumns = ["housing"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        ), ForeignKey(
            entity = ClassroomEntity::class,
            parentColumns = ["id"],
            childColumns = ["classroom"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        ), ForeignKey(
            entity = TypeEntity::class,
            parentColumns = ["id"],
            childColumns = ["type"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        ), ForeignKey(
            entity = TeachersNameEntity::class,
            parentColumns = ["id"],
            childColumns = ["teachersName"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        ), ForeignKey(
            entity = EmailEntity::class,
            parentColumns = ["id"],
            childColumns = ["email"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        ), ForeignKey(
            entity = PhoneEntity::class,
            parentColumns = ["id"],
            childColumns = ["phone"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        ), ForeignKey(
            entity = TimeEntity::class,
            parentColumns = ["id"],
            childColumns = ["time"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        ), ForeignKey(
            entity = DayEntity::class,
            parentColumns = ["id"],
            childColumns = ["day"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        ), ForeignKey(
            entity = WeekTypeEntity::class,
            parentColumns = ["id"],
            childColumns = ["weekType"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        )
    ]
)
data class LessonEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(index = true) val subject: Long = 0,
    @ColumnInfo(index = true) val housing: Long = 0,
    @ColumnInfo(index = true) val classroom: Long = 0,
    @ColumnInfo(index = true) val type: Long = 0,
    @ColumnInfo(index = true) val teachersName: Long = 0,
    @ColumnInfo(index = true) val email: Long = 0,
    @ColumnInfo(index = true) val phone: Long = 0,
    @ColumnInfo(index = true) val time: Long = 0,
    @ColumnInfo(index = true) val day: Long = 0,
    @ColumnInfo(index = true) val weekType: Long = 0
)

@Entity(tableName = "subject_table")
data class SubjectEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(index = true) val subject: String
)

@Entity(tableName = "housing_table")
data class HousingEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(index = true) val housing: String
)

@Entity(tableName = "classroom_table")
data class ClassroomEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(index = true) val classroom: String
)

@Entity(tableName = "type_table")
data class TypeEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(index = true) val type: String
)

@Entity(tableName = "teachers_name_table")
data class TeachersNameEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(index = true) val teachersName: String
)

@Entity(tableName = "email_table")
data class EmailEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(index = true) val email: String
)

@Entity(tableName = "phone_table")
data class PhoneEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(index = true) val phone: String
)

@Entity(tableName = "time_table")
data class TimeEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(index = true) val time: String
)

@Entity(tableName = "day_table")
data class DayEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(index = true) val day: Int
)

@Entity(tableName = "week_type_table")
data class WeekTypeEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(index = true) val weekType: Int
)