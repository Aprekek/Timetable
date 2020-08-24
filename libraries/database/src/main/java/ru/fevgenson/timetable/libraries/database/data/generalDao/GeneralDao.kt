package ru.fevgenson.timetable.libraries.database.data.generalDao

import androidx.room.Dao
import androidx.room.Transaction
import ru.fevgenson.timetable.libraries.database.data.tables.*
import ru.fevgenson.timetable.libraries.database.domain.entities.Lesson

@Dao
abstract class GeneralDao :
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
            teacher = lessonEntity.teacher?.let { getTeacher(it) }
        )
    }

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
                updateTeacher(teacher.copy(id = it))
            } ?: insertTeacher(teacher.copy(id = 0))
        }
    )
}