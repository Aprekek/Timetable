package ru.fevgenson.timetable.shared.lesson.data

import androidx.room.Room
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.fevgenson.timetable.shared.lesson.data.dao.CommonLessonDao
import ru.fevgenson.timetable.shared.lesson.data.database.LessonDatabase
import ru.fevgenson.timetable.shared.lesson.data.dto.LessonDto
import ru.fevgenson.timetable.shared.lesson.data.dto.TeacherDto

@Suppress("IllegalIdentifier")
@RunWith(AndroidJUnit4ClassRunner::class)
class LessonDaoTest {

    private companion object {

        const val SUBJECT1 = "lesson 1"
        const val SUBJECT2 = "lesson 2"

        const val CLASSROOM1 = "classroom1"

        const val HOUSING1 = "housing1"

        const val TEACHER_NAME1 = "teacher name 1"

        const val TIME1 = "time1"

        const val FIRST_WEEK = 0

        const val DAY1 = 0
        const val DAY2 = 1
    }

    private lateinit var dao: CommonLessonDao
    private lateinit var database: LessonDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().context
        database = Room.inMemoryDatabaseBuilder(context, LessonDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = database.lessonDao()
    }

    @After
    fun closeDB() {
        database.close()
    }

    @Test
    fun insetAndReadLessons() {
        runBlocking {
            val lesson1 = LessonDto(
                subject = SUBJECT1,
                time = TIME1,
                day = DAY1,
                weekType = FIRST_WEEK
            )
            val lesson2 = LessonDto(
                subject = SUBJECT2,
                time = TIME1,
                housing = HOUSING1,
                classroom = CLASSROOM1,
                teacher = TeacherDto(
                    name = TEACHER_NAME1
                ),
                day = DAY2,
                weekType = FIRST_WEEK
            )

            dao.insertLesson(lesson1)
            dao.insertLesson(lesson2)

            val lesson1WithId = lesson1.copy(id = 1)
            val lesson2WithId = lesson2.copy(id = 2)

            assertEquals(lesson1WithId, dao.getLesson(1))
            assertEquals(lesson2WithId, dao.getLesson(2))

            assertEquals(dao.getLessons(FIRST_WEEK, DAY1).size, 1)
            assertEquals(dao.getLessons(FIRST_WEEK, DAY2).size, 1)

            assertEquals(lesson1WithId, dao.getLessons(FIRST_WEEK, DAY1).first())
            assertEquals(lesson2WithId, dao.getLessons(FIRST_WEEK, DAY2).first())

            assertEquals(dao.getLessonsFlow(FIRST_WEEK, DAY1).first().size, 1)
            assertEquals(dao.getLessonsFlow(FIRST_WEEK, DAY2).first().size, 1)

            assertEquals(lesson1WithId, dao.getLessonsFlow(FIRST_WEEK, DAY1).first().first())
            assertEquals(lesson2WithId, dao.getLessonsFlow(FIRST_WEEK, DAY2).first().first())

            val allLessons = dao.getAllLessons()
            assertEquals(lesson1WithId, allLessons[0])
            assertEquals(lesson2WithId, allLessons[1])

            val allLessonsFlow = dao.getAllLessonsFlow().first()
            assertEquals(lesson1WithId, allLessonsFlow[0])
            assertEquals(lesson2WithId, allLessonsFlow[1])
        }
    }

    @Test
    fun deleteLessonsById() {
        runBlocking {
            val lesson1 = LessonDto(
                subject = SUBJECT1,
                time = TIME1,
                day = DAY1,
                weekType = FIRST_WEEK
            )
            val lesson2 = LessonDto(
                subject = SUBJECT1,
                time = TIME1,
                housing = HOUSING1,
                classroom = CLASSROOM1,
                teacher = TeacherDto(
                    name = TEACHER_NAME1
                ),
                day = DAY2,
                weekType = FIRST_WEEK
            )

            dao.insertLesson(lesson1)
            dao.insertLesson(lesson2)

            val lesson1WithId = lesson1.copy(id = 1)
            val lesson2WithId = lesson2.copy(id = 2)

            val allLessons = dao.getAllLessons()
            assertEquals(lesson1WithId, allLessons[0])
            assertEquals(lesson2WithId, allLessons[1])

            dao.deleteLesson(1)
            val allLessonsAfterFirstDelete = dao.getAllLessons()
            assertEquals(allLessonsAfterFirstDelete.size, 1)
            assertEquals(lesson2WithId, allLessonsAfterFirstDelete[0])

            dao.deleteLesson(2)
            val allLessonsAfterSecondDelete = dao.getAllLessons()
            assertEquals(allLessonsAfterSecondDelete.size, 0)
        }
    }

    @Test
    fun deleteAllLessons() {
        runBlocking {
            val lesson1 = LessonDto(
                subject = SUBJECT1,
                time = TIME1,
                day = DAY1,
                weekType = FIRST_WEEK
            )
            val lesson2 = LessonDto(
                subject = SUBJECT1,
                time = TIME1,
                housing = HOUSING1,
                classroom = CLASSROOM1,
                teacher = TeacherDto(
                    name = TEACHER_NAME1
                ),
                day = DAY2,
                weekType = FIRST_WEEK
            )

            dao.insertLesson(lesson1)
            dao.insertLesson(lesson2)

            val lesson1WithId = lesson1.copy(id = 1)
            val lesson2WithId = lesson2.copy(id = 2)

            val allLessons = dao.getAllLessons()
            assertEquals(lesson1WithId, allLessons[0])
            assertEquals(lesson2WithId, allLessons[1])

            dao.deleteAllLessons()
            val allLessonsAfterDelete = dao.getAllLessons()
            assertEquals(allLessonsAfterDelete.size, 0)
        }
    }
}