package ru.fevgenson.timetable.libraries.database.data.generalDao

import androidx.room.Dao
import androidx.room.Transaction
import ru.fevgenson.timetable.libraries.database.data.Lesson
import ru.fevgenson.timetable.libraries.database.data.tables.*

@Dao
abstract class GeneralDao :
    LessonDao, SubjectDao, TimeDao, DayDao, WeekTypeDao, HousingDao,
    ClassroomDao, TypeDao, TeachersNameDao, EmailDao, PhoneDao {

    private data class Ids(
        val lessonId: Long? = null,
        val subjectId: Long = 0,
        val timeId: Long = 0,
        val dayId: Long = 0,
        val weekTypeId: Long = 0,
        val housingId: Long? = null,
        val classroomId: Long? = null,
        val typeId: Long? = null,
        val teachersNameId: Long? = null,
        val emailId: Long? = null,
        val phoneId: Long? = null
    )

    @Transaction
    open suspend fun insertLesson(lesson: Lesson): Long {
        val ids = getIds(lesson)

        return insertLesson(
            LessonEntity(
                subject = ids.subjectId,
                time = ids.timeId,
                day = ids.dayId,
                weekType = ids.weekTypeId,
                housing = ids.housingId,
                classroom = ids.classroomId,
                type = ids.typeId,
                teachersName = ids.teachersNameId,
                email = ids.emailId,
                phone = ids.phoneId
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
                day = ids.dayId,
                weekType = ids.weekTypeId,
                housing = ids.housingId,
                classroom = ids.classroomId,
                type = ids.typeId,
                teachersName = ids.teachersNameId,
                email = ids.emailId,
                phone = ids.phoneId
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
            day = getDay(lessonEntity.day).day,
            weekType = getWeekType(lessonEntity.weekType).weekType,
            housing = lessonEntity.housing?.let { getHousing(it).housing },
            classroom = lessonEntity.classroom?.let { getClassroom(it).classroom },
            type = lessonEntity.type?.let { getType(it).type },
            teachersName = lessonEntity.teachersName?.let { getTeachersName(it).teachersName },
            email = lessonEntity.email?.let { getEmail(it).email },
            phone = lessonEntity.phone?.let { getPhone(it).phone }
        )
    }

    private suspend fun getIds(lesson: Lesson) = Ids(
        subjectId = getSubject(lesson.subject)?.id
            ?: insertSubject(SubjectEntity(subject = lesson.subject)),
        timeId = getTime(lesson.time)?.id
            ?: insertTime(TimeEntity(time = lesson.time)),
        dayId = getDay(lesson.day)?.id
            ?: insertDay(DayEntity(day = lesson.day)),
        weekTypeId = getWeekType(lesson.weekType)?.id
            ?: insertWeekType(WeekTypeEntity(weekType = lesson.weekType)),
        housingId = lesson.housing?.let {
            getHousing(it)?.id ?: insertHousing(HousingEntity(housing = it))
        },
        classroomId = lesson.classroom?.let {
            getClassroom(it)?.id ?: insertClassroom(ClassroomEntity(classroom = it))
        },
        typeId = lesson.type?.let {
            getType(it)?.id ?: insertType(TypeEntity(type = it))
        },
        teachersNameId = lesson.teachersName?.let {
            getTeachersName(it)?.id ?: insertTeachersName(TeachersNameEntity(teachersName = it))
        },
        emailId = lesson.email?.let {
            getEmail(it)?.id ?: insertEmail(EmailEntity(email = it))
        },
        phoneId = lesson.phone?.let {
            getPhone(it)?.id ?: insertPhone(PhoneEntity(phone = it))
        }
    )
}