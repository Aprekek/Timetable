package ru.fevgenson.timetable.libraries.database.data.generalDao

import androidx.room.Dao
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.fevgenson.timetable.libraries.database.data.tables.*
import ru.fevgenson.timetable.shared.lesson.domain.entities.Categories
import ru.fevgenson.timetable.shared.lesson.domain.entities.Lesson

@Dao
internal abstract class GeneralDao :
    LessonDao, SubjectDao, TimeDao, HousingDao,
    ClassroomDao, TypeDao, TeachersNameDao {

    private data class Ids(
        val lessonId: Long? = null,
        val subjectId: Long = 0,
        val timeId: Long = 0,
        val housingId: Long? = null,
        val classroomId: Long? = null,
        val typeId: Long? = null,
        val teacherId: Long? = null
    )

    @Transaction
    open suspend fun insertLesson(lesson: Lesson): Long {
        val ids = getIds(lesson)

        return insertLesson(
            LessonEntity(
                subject = ids.subjectId,
                time = ids.timeId,
                day = lesson.day,
                weekType = lesson.weekType,
                housing = ids.housingId,
                classroom = ids.classroomId,
                type = ids.typeId,
                teacher = ids.teacherId
            )
        )
    }

    @Transaction
    open suspend fun updateLesson(lesson: Lesson) {
        val ids = getIds(lesson)

        updateLesson(
            LessonEntity(
                id = lesson.id,
                subject = ids.subjectId,
                time = ids.timeId,
                day = lesson.day,
                weekType = lesson.weekType,
                housing = ids.housingId,
                classroom = ids.classroomId,
                type = ids.typeId,
                teacher = ids.teacherId
            )
        )
    }

    @Transaction
    open suspend fun getLessonForEdit(lessonId: Long): Lesson {
        val lessonEntity = getLesson(lessonId)

        return Lesson(
            id = lessonEntity.id,
            subject = getSubject(lessonEntity.subject).subject,
            time = getTime(lessonEntity.time).time,
            day = lessonEntity.day,
            weekType = lessonEntity.weekType,
            housing = lessonEntity.housing?.let { getHousing(it).housing },
            classroom = lessonEntity.classroom?.let { getClassroom(it).classroom },
            type = lessonEntity.type?.let { getType(it).type },
            teacher = lessonEntity.teacher?.let { getTeacher(it).toDomainTeacherEntity() }
        )
    }

    open fun getLessonsForEdit(
        weekType: Int,
        day: Int
    ): Flow<List<Lesson>> = getLessons(weekType, day).map { entityList ->
        entityList.map { lessonEntity ->
            Lesson(
                id = lessonEntity.id,
                subject = getSubject(lessonEntity.subject).subject,
                time = getTime(lessonEntity.time).time,
                day = lessonEntity.day,
                weekType = lessonEntity.weekType,
                housing = lessonEntity.housing?.let { getHousing(it).housing },
                classroom = lessonEntity.classroom?.let { getClassroom(it).classroom },
                type = lessonEntity.type?.let { getType(it).type },
                teacher = lessonEntity.teacher?.let { getTeacher(it).toDomainTeacherEntity() }
            )
        }.sortedBy { it.time }
    }

    open fun getLessonsBySubcategory(
        category: Int,
        subcategoryName: String
    ): Flow<List<Lesson>> {
        return when (category) {
            Categories.SUBJECT_CATEGORY -> getLessonsBySubject(subcategoryName).map { relation ->
                relation.lessons.map { lessonEntity ->
                    lessonEntity.toLesson()
                }
            }
            Categories.TEACHER_CATEGORY -> getLessonsByTeacher(subcategoryName).map { relation ->
                relation.lessons.map { lessonEntity ->
                    lessonEntity.toLesson()
                }
            }
            Categories.CLASSROOM_CATEGORY -> getLessonsByClassroom(subcategoryName).map { relation ->
                relation.lessons.map { lessonEntity ->
                    lessonEntity.toLesson()
                }
            }
            Categories.HOUSING_CATEGORY -> getLessonsByHousing(subcategoryName).map { relation ->
                relation.lessons.map { lessonEntity ->
                    lessonEntity.toLesson()
                }
            }
            Categories.TIME_CATEGORY -> getLessonsByTime(subcategoryName).map { relation ->
                relation.lessons.map { lessonEntity ->
                    lessonEntity.toLesson()
                }
            }
            else -> throw IllegalStateException("$category does not defined")
        }
    }

    private suspend fun LessonEntity.toLesson() = Lesson(
        id = id,
        subject = getSubject(subject).subject,
        time = getTime(time).time,
        day = day,
        weekType = weekType,
        housing = housing?.let { getHousing(it).housing },
        classroom = classroom?.let { getClassroom(it).classroom },
        type = type?.let { getType(it).type },
        teacher = teacher?.let { getTeacher(it).toDomainTeacherEntity() }
    )

    private suspend fun getIds(lesson: Lesson) = Ids(
        subjectId = getSubject(lesson.subject)?.id
            ?: insertSubject(SubjectEntity(subject = lesson.subject)),
        timeId = getTime(lesson.time)?.id
            ?: insertTime(TimeEntity(time = lesson.time)),
        housingId = lesson.housing?.let {
            getHousing(it)?.id ?: insertHousing(HousingEntity(housing = it))
        },
        classroomId = lesson.classroom?.let {
            getClassroom(it)?.id ?: insertClassroom(ClassroomEntity(classroom = it))
        },
        typeId = lesson.type?.let {
            getType(it)?.id ?: insertType(TypeEntity(type = it))
        },
        teacherId = lesson.teacher?.let { teacher ->
            getTeacher(teacher.name)?.id?.also {
                with(teacher) {
                    updateTeacher(TeacherEntity(id = it, name, phone, email))
                }
            } ?: with(teacher) { insertTeacher(TeacherEntity(id = 0, name, phone, email)) }
        }
    )
}