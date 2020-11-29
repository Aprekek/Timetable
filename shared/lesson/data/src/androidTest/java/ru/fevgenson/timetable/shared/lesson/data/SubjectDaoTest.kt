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
import ru.fevgenson.timetable.shared.lesson.data.dto.SubjectDto

@Suppress("IllegalIdentifier")
@RunWith(AndroidJUnit4ClassRunner::class)
class SubjectDaoTest {

    private companion object {

        const val SUBJECT1 = "subject1"
        const val SUBJECT2 = "subject2"
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
    fun insetAndReadSubjects() {
        runBlocking {
            val subject1 = SubjectDto(subject = SUBJECT1)
            val subject2 = SubjectDto(subject = SUBJECT2)

            dao.insertSubject(subject1)
            dao.insertSubject(subject2)

            val subject1WithId = subject1.copy(id = 1)
            val subject2WithId = subject2.copy(id = 2)

            assertEquals(subject1WithId, dao.getSubject(1))
            assertEquals(subject2WithId, dao.getSubject(2))

            assertEquals(subject1WithId, dao.getSubject(SUBJECT1))
            assertEquals(subject2WithId, dao.getSubject(SUBJECT2))

            val allSubjects = dao.getAllSubjects()
            assertEquals(subject1WithId, allSubjects[0])
            assertEquals(subject2WithId, allSubjects[1])

            val allSubjectsFlow = dao.getAllSubjectsFlow().first()
            assertEquals(subject1WithId, allSubjectsFlow[0])
            assertEquals(subject2WithId, allSubjectsFlow[1])
        }
    }

    @Test
    fun deleteSubjectsById() {
        runBlocking {
            val subject1 = SubjectDto(subject = SUBJECT1)
            val subject2 = SubjectDto(subject = SUBJECT2)

            dao.insertSubject(subject1)
            dao.insertSubject(subject2)

            val subject1WithId = subject1.copy(id = 1)
            val subject2WithId = subject2.copy(id = 2)

            val allSubjects = dao.getAllSubjects()
            assertEquals(subject1WithId, allSubjects[0])
            assertEquals(subject2WithId, allSubjects[1])

            dao.deleteSubject(1)
            val allSubjectsAfterFirstDelete = dao.getAllSubjects()
            assertEquals(allSubjectsAfterFirstDelete.size, 1)
            assertEquals(subject2WithId, allSubjectsAfterFirstDelete[0])

            dao.deleteSubject(2)
            val allSubjectsAfterSecondDelete = dao.getAllSubjects()
            assertEquals(allSubjectsAfterSecondDelete.size, 0)
        }
    }

    @Test
    fun deleteAllSubjects() {
        runBlocking {
            val subject1 = SubjectDto(subject = SUBJECT1)
            val subject2 = SubjectDto(subject = SUBJECT2)

            dao.insertSubject(subject1)
            dao.insertSubject(subject2)

            val subject1WithId = subject1.copy(id = 1)
            val subject2WithId = subject2.copy(id = 2)

            val allSubjects = dao.getAllSubjects()
            assertEquals(subject1WithId, allSubjects[0])
            assertEquals(subject2WithId, allSubjects[1])

            dao.deleteAllSubjects()
            val allSubjectsAfterDelete = dao.getAllSubjects()
            assertEquals(allSubjectsAfterDelete.size, 0)
        }
    }

    @Test
    fun getSubjectIdOrInsert() {
        runBlocking {
            val id = dao.getSubjectIdOrInsert(SUBJECT1)
            assertEquals(id, 1)
            assertEquals(dao.getAllSubjects().size, 1)

            val secondCallId = dao.getSubjectIdOrInsert(SUBJECT1)
            assertEquals(secondCallId, id)
            assertEquals(dao.getAllSubjects().size, 1)
        }
    }
}