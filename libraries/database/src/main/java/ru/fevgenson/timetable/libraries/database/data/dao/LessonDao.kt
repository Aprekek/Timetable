package ru.fevgenson.timetable.libraries.database.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.fevgenson.timetable.libraries.database.data.Lesson
import ru.fevgenson.timetable.libraries.database.data.entities.*

@Dao
abstract class DaoImplementations :
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

@Dao
interface LessonDao {

    @Query("SELECT * from lesson_table WHERE day = :day AND weekType = :week")
    fun getLessonsForDay(day: Int, week: Int): LiveData<List<LessonEntity>>

    @Query("SELECT * from lesson_table WHERE id = :id")
    fun getLesson(id: Long): LessonEntity

    @Insert
    fun insertLesson(lesson: LessonEntity): Long

    @Update
    suspend fun updateLesson(lesson: LessonEntity)

    @Delete
    suspend fun deleteLesson(lesson: LessonEntity)

    @Query("DELETE from lesson_table")
    suspend fun deleteAllLessons()
}

@Dao
interface SubjectDao {

    @Query("SELECT * from subject_table")
    suspend fun getSubjects(): List<SubjectEntity>

    @Query("SELECT * from subject_table WHERE subject = :subject")
    suspend fun getSubject(subject: String): SubjectEntity?

    @Query("SELECT * from subject_table WHERE id = :id")
    suspend fun getSubject(id: Long): SubjectEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubject(subject: SubjectEntity): Long

    @Update
    suspend fun updateSubject(subject: SubjectEntity)

    @Delete
    suspend fun deleteSubject(subject: SubjectEntity)
}

@Dao
interface TimeDao {

    @Query("SELECT * from time_table")
    suspend fun getTimes(): List<TimeEntity>

    @Query("SELECT * from time_table WHERE time = :time")
    suspend fun getTime(time: String): TimeEntity?

    @Query("SELECT * from time_table WHERE id = :id")
    suspend fun getTime(id: Long): TimeEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTime(time: TimeEntity): Long

    @Update
    suspend fun updateTime(time: TimeEntity)

    @Delete
    suspend fun deleteTime(time: TimeEntity)
}

@Dao
interface DayDao {

    @Query("SELECT * from day_table")
    suspend fun getDays(): List<DayEntity>

    @Query("SELECT * from day_table WHERE day = :day")
    suspend fun getDay(day: Int): DayEntity?

    @Query("SELECT * from day_table WHERE id = :id")
    suspend fun getDay(id: Long): DayEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDay(day: DayEntity): Long

    @Update
    suspend fun updateDay(day: DayEntity)

    @Delete
    suspend fun deleteDay(day: DayEntity)
}

@Dao
interface WeekTypeDao {

    @Query("SELECT * from week_type_table")
    suspend fun getWeekTypes(): List<WeekTypeEntity>

    @Query("SELECT * from week_type_table WHERE weekType = :weekType")
    suspend fun getWeekType(weekType: Int): WeekTypeEntity?

    @Query("SELECT * from week_type_table WHERE id = :id")
    suspend fun getWeekType(id: Long): WeekTypeEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeekType(weekType: WeekTypeEntity): Long

    @Update
    suspend fun updateWeekType(weekType: WeekTypeEntity)

    @Delete
    suspend fun deleteWeekType(weekType: WeekTypeEntity)
}

@Dao
interface HousingDao {

    @Query("SELECT * from housing_table")
    suspend fun getHousings(): List<HousingEntity>

    @Query("SELECT * from housing_table WHERE housing = :housing")
    suspend fun getHousing(housing: String): HousingEntity?

    @Query("SELECT * from housing_table WHERE id = :id")
    suspend fun getHousing(id: Long): HousingEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHousing(housing: HousingEntity): Long

    @Update
    suspend fun updateHousing(housing: HousingEntity)

    @Delete
    suspend fun deleteHousing(housing: HousingEntity)
}

@Dao
interface ClassroomDao {

    @Query("SELECT * from classroom_table")
    suspend fun getClassrooms(): List<ClassroomEntity>

    @Query("SELECT * from classroom_table WHERE classroom = :classroom")
    suspend fun getClassroom(classroom: String): ClassroomEntity?

    @Query("SELECT * from classroom_table WHERE id = :id")
    suspend fun getClassroom(id: Long): ClassroomEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClassroom(classroom: ClassroomEntity): Long

    @Update
    suspend fun updateClassroom(classroom: ClassroomEntity)

    @Delete
    suspend fun deleteClassroom(classroom: ClassroomEntity)
}

@Dao
interface TypeDao {

    @Query("SELECT * from type_table")
    suspend fun getTypes(): List<TypeEntity>

    @Query("SELECT * from type_table WHERE type = :type")
    suspend fun getType(type: String): TypeEntity?

    @Query("SELECT * from type_table WHERE id = :id")
    suspend fun getType(id: Long): TypeEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertType(type: TypeEntity): Long

    @Update
    suspend fun updateType(type: TypeEntity)

    @Delete
    suspend fun deleteType(type: TypeEntity)
}

@Dao
interface TeachersNameDao {

    @Query("SELECT * from teachers_name_table")
    suspend fun getTeachersNames(): List<TeachersNameEntity>

    @Query("SELECT * from teachers_name_table WHERE teachersName = :teachersName")
    suspend fun getTeachersName(teachersName: String): TeachersNameEntity?

    @Query("SELECT * from teachers_name_table WHERE id = :id")
    suspend fun getTeachersName(id: Long): TeachersNameEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeachersName(teachersName: TeachersNameEntity): Long

    @Update
    suspend fun updateTeachersName(teachersName: TeachersNameEntity)

    @Delete
    suspend fun deleteTeachersName(teachersName: TeachersNameEntity)
}

@Dao
interface EmailDao {

    @Query("SELECT * from email_table")
    suspend fun getEmails(): List<EmailEntity>

    @Query("SELECT * from email_table WHERE email = :email")
    suspend fun getEmail(email: String): EmailEntity?

    @Query("SELECT * from email_table WHERE id = :id")
    suspend fun getEmail(id: Long): EmailEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmail(email: EmailEntity): Long

    @Update
    suspend fun updateEmail(email: EmailEntity)

    @Delete
    suspend fun deleteEmail(email: EmailEntity)
}

@Dao
interface PhoneDao {

    @Query("SELECT * from phone_table")
    suspend fun getPhones(): List<PhoneEntity>

    @Query("SELECT * from phone_table WHERE phone = :phone")
    suspend fun getPhone(phone: String): PhoneEntity?

    @Query("SELECT * from phone_table WHERE id = :id")
    suspend fun getPhone(id: Long): PhoneEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhone(phone: PhoneEntity): Long

    @Update
    suspend fun updatePhone(phone: PhoneEntity)

    @Delete
    suspend fun deletePhone(phone: PhoneEntity)
}
