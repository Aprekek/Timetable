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
import ru.fevgenson.timetable.shared.lesson.data.dto.TeacherDto

@Suppress("IllegalIdentifier")
@RunWith(AndroidJUnit4ClassRunner::class)
class TeacherDaoTest {

    private companion object {

        const val TEACHER_NAME1 = "teacher name 1"
        const val TEACHER_NAME2 = "teacher name 2"
        const val TEACHER_EMAIL2 = "teacher email 2"
        const val TEACHER_PHONE2 = "teacher phone 2"
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
    fun insetAndReadTeachers() {
        runBlocking {
            val teacher1 = TeacherDto(name = TEACHER_NAME1)
            val teacher2 = TeacherDto(
                name = TEACHER_NAME2,
                email = TEACHER_EMAIL2,
                phone = TEACHER_PHONE2
            )

            dao.insertTeacher(teacher1)
            dao.insertTeacher(teacher2)

            val teacher1WithId = teacher1.copy(id = 1)
            val teacher2WithId = teacher2.copy(id = 2)

            assertEquals(teacher1WithId, dao.getTeacher(1))
            assertEquals(teacher2WithId, dao.getTeacher(2))

            assertEquals(teacher1WithId, dao.getTeacher(TEACHER_NAME1))
            assertEquals(teacher2WithId, dao.getTeacher(TEACHER_NAME2))

            val allTeachers = dao.getAllTeachers()
            assertEquals(teacher1WithId, allTeachers[0])
            assertEquals(teacher2WithId, allTeachers[1])

            val allTeachersFlow = dao.getAllTeachersFlow().first()
            assertEquals(teacher1WithId, allTeachersFlow[0])
            assertEquals(teacher2WithId, allTeachersFlow[1])
        }
    }

    @Test
    fun deleteTeachersById() {
        runBlocking {
            val teacher1 = TeacherDto(name = TEACHER_NAME1)
            val teacher2 = TeacherDto(
                name = TEACHER_NAME2,
                email = TEACHER_EMAIL2,
                phone = TEACHER_PHONE2
            )

            dao.insertTeacher(teacher1)
            dao.insertTeacher(teacher2)

            val teacher1WithId = teacher1.copy(id = 1)
            val teacher2WithId = teacher2.copy(id = 2)

            val allTeachers = dao.getAllTeachers()
            assertEquals(teacher1WithId, allTeachers[0])
            assertEquals(teacher2WithId, allTeachers[1])

            dao.deleteTeacher(1)
            val allTeachersAfterFirstDelete = dao.getAllTeachers()
            assertEquals(allTeachersAfterFirstDelete.size, 1)
            assertEquals(teacher2WithId, allTeachersAfterFirstDelete[0])

            dao.deleteTeacher(2)
            val allTeachersAfterSecondDelete = dao.getAllTeachers()
            assertEquals(allTeachersAfterSecondDelete.size, 0)
        }
    }

    @Test
    fun deleteAllTeachers() {
        runBlocking {
            val teacher1 = TeacherDto(name = TEACHER_NAME1)
            val teacher2 = TeacherDto(
                name = TEACHER_NAME2,
                email = TEACHER_EMAIL2,
                phone = TEACHER_PHONE2
            )

            dao.insertTeacher(teacher1)
            dao.insertTeacher(teacher2)

            val teacher1WithId = teacher1.copy(id = 1)
            val teacher2WithId = teacher2.copy(id = 2)

            val allTeachers = dao.getAllTeachers()
            assertEquals(teacher1WithId, allTeachers[0])
            assertEquals(teacher2WithId, allTeachers[1])

            dao.deleteAllTeachers()
            val allTeachersAfterDelete = dao.getAllTeachers()
            assertEquals(allTeachersAfterDelete.size, 0)
        }
    }

    @Test
    fun getTeacherIdOrInsert() {
        runBlocking {
            val id = dao.getTeacherIdOrInsert(TEACHER_NAME1, TEACHER_EMAIL2, null)
            assertEquals(id, 1)
            assertEquals(dao.getAllTeachers().size, 1)

            val secondCallId = dao.getTeacherIdOrInsert(TEACHER_NAME1, TEACHER_EMAIL2, null)
            assertEquals(secondCallId, id)
            assertEquals(dao.getAllTeachers().size, 1)
        }
    }
}