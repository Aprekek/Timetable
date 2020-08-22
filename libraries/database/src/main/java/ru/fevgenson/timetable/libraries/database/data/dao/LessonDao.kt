package ru.fevgenson.timetable.libraries.database.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.fevgenson.timetable.libraries.database.data.entities.*

@Dao
abstract class DaoImplementations :
    LessonDao, SubjectDao, TimeDao, DayDao, WeekTypeDao, HousingDao,
    ClassroomDao, TypeDao, TeachersNameDao, EmailDao, PhoneDao

@Dao
interface LessonDao {

    @Query("SELECT * from lesson_table WHERE day = :day AND weekType = :week")
    fun getLessonsForDay(day: Int, week: Int): LiveData<List<LessonEntity>>

    @Query("SELECT * from lesson_table WHERE id = :id")
    suspend fun getLesson(id: Int): LessonEntity

    @Insert
    suspend fun insertLesson(lesson: LessonEntity)

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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPhone(phone: PhoneEntity): Long

    @Update
    suspend fun updatePhone(phone: PhoneEntity)

    @Delete
    suspend fun deletePhone(phone: PhoneEntity)
}
