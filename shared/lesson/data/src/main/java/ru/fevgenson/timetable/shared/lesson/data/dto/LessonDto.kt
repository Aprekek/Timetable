package ru.fevgenson.timetable.shared.lesson.data.dto

import androidx.room.*

@Entity(
    tableName = "lesson_table",
    foreignKeys = [
        ForeignKey(
            entity = SubjectDto::class,
            parentColumns = ["subject_id"],
            childColumns = ["subject_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        ), ForeignKey(
            entity = HousingDto::class,
            parentColumns = ["housing_id"],
            childColumns = ["housing_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        ), ForeignKey(
            entity = ClassroomDto::class,
            parentColumns = ["classroom_id"],
            childColumns = ["classroom_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        ), ForeignKey(
            entity = TypeDto::class,
            parentColumns = ["type_id"],
            childColumns = ["type_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        ), ForeignKey(
            entity = TeacherDto::class,
            parentColumns = ["teacher_id"],
            childColumns = ["teacher_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        ), ForeignKey(
            entity = TimeDto::class,
            parentColumns = ["time_id"],
            childColumns = ["time_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        )
    ]
)
data class LessonIdsDto(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "lesson_id")
    val id: Long = 0,
    @ColumnInfo(index = true, name = "subject_id")
    val subjectId: Long = 0,
    @ColumnInfo(index = true, name = "time_id")
    val timeId: Long = 0,
    @ColumnInfo(index = true, name = "housing_id")
    val housingId: Long? = null,
    @ColumnInfo(index = true, name = "classroom_id")
    val classroomId: Long? = null,
    @ColumnInfo(index = true, name = "type_id")
    val typeId: Long? = null,
    @ColumnInfo(index = true, name = "teacher_id")
    val teacherId: Long? = null,
    @ColumnInfo(index = true, name = "day")
    val day: Int = 0,
    @ColumnInfo(index = true, name = "week_type")
    val weekType: Int = 0,
)

data class LessonDto(
    @ColumnInfo(name = "lesson_id")
    val id: Long = 0,
    @Relation(
        parentColumn = "lesson_id",
        entityColumn = "subject_id",
        entity = SubjectDto::class,
        associateBy = Junction(LessonIdsDto::class),
        projection = ["subject"]
    )
    val subject: String,
    @Relation(
        parentColumn = "lesson_id",
        entityColumn = "time_id",
        entity = TimeDto::class,
        associateBy = Junction(LessonIdsDto::class),
        projection = ["time"]
    )
    val time: String,
    @Relation(
        parentColumn = "lesson_id",
        entityColumn = "housing_id",
        entity = HousingDto::class,
        associateBy = Junction(LessonIdsDto::class),
        projection = ["housing"]
    )
    val housing: String? = null,
    @Relation(
        parentColumn = "lesson_id",
        entityColumn = "classroom_id",
        entity = ClassroomDto::class,
        associateBy = Junction(LessonIdsDto::class),
        projection = ["classroom"]
    )
    val classroom: String? = null,
    @Relation(
        parentColumn = "lesson_id",
        entityColumn = "type_id",
        entity = TypeDto::class,
        associateBy = Junction(LessonIdsDto::class),
        projection = ["type"]
    )
    val type: String? = null,
    @Relation(
        parentColumn = "lesson_id",
        entityColumn = "teacher_id",
        entity = TeacherDto::class,
        associateBy = Junction(LessonIdsDto::class),
        projection = ["name"]
    )
    val teacher: TeacherDto? = null,
    @ColumnInfo(name = "day")
    val day: Int,
    @ColumnInfo(name = "week_type")
    val weekType: Int,
)