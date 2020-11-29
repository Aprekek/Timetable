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
import ru.fevgenson.timetable.shared.lesson.data.dto.ClassroomDto

@Suppress("IllegalIdentifier")
@RunWith(AndroidJUnit4ClassRunner::class)
class ClassroomDaoTest {

    private companion object {

        const val CLASSROOM1 = "classroom1"
        const val CLASSROOM2 = "classroom2"
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
    fun insetAndReadClassrooms() {
        runBlocking {
            val classroom1 = ClassroomDto(classroom = CLASSROOM1)
            val classroom2 = ClassroomDto(classroom = CLASSROOM2)

            dao.insertClassroom(classroom1)
            dao.insertClassroom(classroom2)

            val classroom1WithId = classroom1.copy(id = 1)
            val classroom2WithId = classroom2.copy(id = 2)

            assertEquals(classroom1WithId, dao.getClassroom(1))
            assertEquals(classroom2WithId, dao.getClassroom(2))

            assertEquals(classroom1WithId, dao.getClassroom(CLASSROOM1))
            assertEquals(classroom2WithId, dao.getClassroom(CLASSROOM2))

            val allClassrooms = dao.getAllClassrooms()
            assertEquals(classroom1WithId, allClassrooms[0])
            assertEquals(classroom2WithId, allClassrooms[1])

            val allClassroomsFlow = dao.getAllClassroomsFlow().first()
            assertEquals(classroom1WithId, allClassroomsFlow[0])
            assertEquals(classroom2WithId, allClassroomsFlow[1])
        }
    }

    @Test
    fun deleteClassroomsById() {
        runBlocking {
            val classroom1 = ClassroomDto(classroom = CLASSROOM1)
            val classroom2 = ClassroomDto(classroom = CLASSROOM2)

            dao.insertClassroom(classroom1)
            dao.insertClassroom(classroom2)

            val classroom1WithId = classroom1.copy(id = 1)
            val classroom2WithId = classroom2.copy(id = 2)

            val allClassrooms = dao.getAllClassrooms()
            assertEquals(classroom1WithId, allClassrooms[0])
            assertEquals(classroom2WithId, allClassrooms[1])

            dao.deleteClassroom(1)
            val allClassroomsAfterFirstDelete = dao.getAllClassrooms()
            assertEquals(allClassroomsAfterFirstDelete.size, 1)
            assertEquals(classroom2WithId, allClassroomsAfterFirstDelete[0])

            dao.deleteClassroom(2)
            val allClassroomsAfterSecondDelete = dao.getAllClassrooms()
            assertEquals(allClassroomsAfterSecondDelete.size, 0)
        }
    }

    @Test
    fun deleteAllClassrooms() {
        runBlocking {
            val classroom1 = ClassroomDto(classroom = CLASSROOM1)
            val classroom2 = ClassroomDto(classroom = CLASSROOM2)

            dao.insertClassroom(classroom1)
            dao.insertClassroom(classroom2)

            val classroom1WithId = classroom1.copy(id = 1)
            val classroom2WithId = classroom2.copy(id = 2)

            val allClassrooms = dao.getAllClassrooms()
            assertEquals(classroom1WithId, allClassrooms[0])
            assertEquals(classroom2WithId, allClassrooms[1])

            dao.deleteAllClassrooms()
            val allClassroomsAfterDelete = dao.getAllClassrooms()
            assertEquals(allClassroomsAfterDelete.size, 0)
        }
    }

    @Test
    fun getClassroomIdOrInsert() {
        runBlocking {
            val id = dao.getClassroomIdOrInsert(CLASSROOM1)
            assertEquals(id, 1)
            assertEquals(dao.getAllClassrooms().size, 1)

            val secondCallId = dao.getClassroomIdOrInsert(CLASSROOM1)
            assertEquals(secondCallId, id)
            assertEquals(dao.getAllClassrooms().size, 1)
        }
    }
}