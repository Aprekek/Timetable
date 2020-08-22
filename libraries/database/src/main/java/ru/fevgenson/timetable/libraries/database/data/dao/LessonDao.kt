package ru.fevgenson.timetable.libraries.database.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.fevgenson.timetable.libraries.database.data.Lesson
import ru.fevgenson.timetable.libraries.database.data.entities.*

@Dao
abstract class DaoImplementations :
    LessonDao, SubjectDao, TimeDao, DayDao, WeekTypeDao, HousingDao,
    ClassroomDao, TypeDao, TeachersNameDao, EmailDao, PhoneDao {

    @Transaction
    open fun insertLesson(lesson: Lesson) {

        val subjectId =
            getSubject(lesson.subject)?.id ?: insertSubject(SubjectEntity(subject = lesson.subject))
        val timeId =
            getTime(lesson.time)?.id ?: insertTime(TimeEntity(time = lesson.time))
        val dayId =
            getDay(lesson.day)?.id ?: insertDay(DayEntity(day = lesson.day))
        val weekTypeId =
            getWeekType(lesson.weekType)?.id
                ?: insertWeekType(WeekTypeEntity(weekType = lesson.weekType))
        val housingId = lesson.housing?.let {
            getHousing(it)?.id ?: insertHousing(HousingEntity(housing = it))
        }
        val classroomId = lesson.classroom?.let {
            getClassroom(it)?.id ?: insertClassroom(ClassroomEntity(classroom = it))
        }
        val typeId = lesson.type?.let {
            getType(it)?.id ?: insertType(TypeEntity(type = it))
        }
        val teachersNameId = lesson.teachersName?.let {
            getTeachersName(it)?.id ?: insertTeachersName(TeachersNameEntity(teachersName = it))
        }
        val emailId = lesson.email?.let {
            getEmail(it)?.id ?: insertEmail(EmailEntity(email = it))
        }
        val phoneId = lesson.phone?.let {
            getPhone(it)?.id ?: insertPhone(PhoneEntity(phone = it))
        }

        insertLesson(
            LessonEntity(
                subject = subjectId,
                time = timeId,
                day = dayId,
                weekType = weekTypeId,
                housing = housingId,
                classroom = classroomId,
                type = typeId,
                teachersName = teachersNameId,
                email = emailId,
                phone = phoneId
            )
        )
    }
}

@Dao
interface LessonDao {

    @Query("SELECT * from lesson_table WHERE day = :day AND weekType = :week")
    fun getLessonsForDay(day: Int, week: Int): LiveData<List<LessonEntity>>

    @Query("SELECT * from lesson_table WHERE id = :id")
    fun getLesson(id: Int): LessonEntity

    @Insert
    fun insertLesson(lesson: LessonEntity)

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
    fun getSubject(subject: String): SubjectEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSubject(subject: SubjectEntity): Long

    @Update
    fun updateSubject(subject: SubjectEntity)

    @Delete
    suspend fun deleteSubject(subject: SubjectEntity)
}

@Dao
interface TimeDao {

    @Query("SELECT * from time_table")
    suspend fun getTimes(): List<TimeEntity>

    @Query("SELECT * from time_table WHERE time = :time")
    fun getTime(time: String): TimeEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTime(time: TimeEntity): Long

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
    fun getDay(day: Int): DayEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertDay(day: DayEntity): Long

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
    fun getWeekType(weekType: Int): WeekTypeEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertWeekType(weekType: WeekTypeEntity): Long

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
    fun getHousing(housing: String): HousingEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertHousing(housing: HousingEntity): Long

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
    fun getClassroom(classroom: String): ClassroomEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertClassroom(classroom: ClassroomEntity): Long

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
    fun getType(type: String): TypeEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertType(type: TypeEntity): Long

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
    fun getTeachersName(teachersName: String): TeachersNameEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTeachersName(teachersName: TeachersNameEntity): Long

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
    fun getEmail(email: String): EmailEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertEmail(email: EmailEntity): Long

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
    fun getPhone(phone: String): PhoneEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPhone(phone: PhoneEntity): Long

    @Update
    suspend fun updatePhone(phone: PhoneEntity)

    @Delete
    suspend fun deletePhone(phone: PhoneEntity)
}
