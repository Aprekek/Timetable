package ru.fevgenson.timetable.shared.lesson.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import ru.fevgenson.timetable.shared.lesson.data.dto.LessonDto
import ru.fevgenson.timetable.shared.lesson.data.dto.LessonIdsDto

@Dao
abstract class CommonLessonDao : ClassroomDao, HousingDao, LessonDao, SubjectDao, TeacherDao,
    TimeDao,
    TypeDao {

    suspend fun insertLesson(lessonDto: LessonDto) {
        with(lessonDto) {
            val lessonIdsDto = LessonIdsDto(
                id = id,
                subjectId = getSubjectIdOrInsert(subject),
                timeId = getTimeIdOrInsert(time),
                housingId = housing?.let { getHousingIdOrInsert(it) },
                classroomId = classroom?.let { getClassroomIdOrInsert(it) },
                typeId = type?.let { getTypeIdOrInsert(it) },
                teacherId = teacher?.let {
                    getTeacherIdOrInsert(
                        name = it.name,
                        email = it.email,
                        phone = it.phone
                    )
                },
                day = day,
                weekType = weekType
            )
            insertLesson(lessonIdsDto)
        }
    }

    @Transaction
    @Query("SELECT lesson_id, week_type, day FROM lesson_table WHERE lesson_id == :id")
    abstract suspend fun getLesson(id: Long): LessonDto

    @Transaction
    @Query("SELECT lesson_id, week_type, day FROM lesson_table")
    abstract suspend fun getAllLessons(): List<LessonDto>

    @Transaction
    @Query("SELECT lesson_id, week_type, day FROM lesson_table")
    abstract fun getAllLessonsFlow(): Flow<List<LessonDto>>

    @Transaction
    @Query("SELECT lesson_id, week_type, day FROM lesson_table WHERE week_type == :weekType AND day == :day")
    abstract suspend fun getLessons(weekType: Int, day: Int): List<LessonDto>

    @Transaction
    @Query("SELECT lesson_id, week_type, day FROM lesson_table WHERE week_type == :weekType AND day == :day")
    abstract fun getLessonsFlow(weekType: Int, day: Int): Flow<List<LessonDto>>
}