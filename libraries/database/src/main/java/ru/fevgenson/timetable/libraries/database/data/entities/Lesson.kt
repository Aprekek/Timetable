package ru.fevgenson.timetable.libraries.database.data.entities

import androidx.room.*

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
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(index = true) val subject: Long = 0,
    @ColumnInfo(index = true) val time: Long = 0,
    @ColumnInfo(index = true) val day: Long = 0,
    @ColumnInfo(index = true) val weekType: Long = 0,
    @ColumnInfo(index = true) val housing: Long? = null,
    @ColumnInfo(index = true) val classroom: Long? = null,
    @ColumnInfo(index = true) val type: Long? = null,
    @ColumnInfo(index = true) val teachersName: Long? = null,
    @ColumnInfo(index = true) val email: Long? = null,
    @ColumnInfo(index = true) val phone: Long? = null
)

@Entity(tableName = "subject_table", indices = [Index(value = ["subject"], unique = true)])
data class SubjectEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val subject: String
)

@Entity(tableName = "housing_table", indices = [Index(value = ["housing"], unique = true)])
data class HousingEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val housing: String
)

@Entity(tableName = "classroom_table", indices = [Index(value = ["classroom"], unique = true)])
data class ClassroomEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val classroom: String
)

@Entity(tableName = "type_table", indices = [Index(value = ["type"], unique = true)])
data class TypeEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val type: String
)

@Entity(
    tableName = "teachers_name_table",
    indices = [Index(value = ["teachersName"], unique = true)]
)
data class TeachersNameEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val teachersName: String
)

@Entity(
    tableName = "email_table",
    indices = [androidx.room.Index(value = ["email"], unique = true)]
)
data class EmailEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val email: String
)

@Entity(tableName = "phone_table", indices = [Index(value = ["phone"], unique = true)])
data class PhoneEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val phone: String
)

@Entity(tableName = "time_table", indices = [Index(value = ["time"], unique = true)])
data class TimeEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val time: String
)

@Entity(tableName = "day_table", indices = [Index(value = ["day"], unique = true)])
data class DayEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val day: Int
)

@Entity(tableName = "week_type_table", indices = [Index(value = ["weekType"], unique = true)])
data class WeekTypeEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val weekType: Int
)