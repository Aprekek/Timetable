package ru.fevgenson.timetable.libraries.database.data

import androidx.room.Room
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.fevgenson.timetable.libraries.database.data.database.LessonDatabase
import ru.fevgenson.timetable.libraries.database.data.generalDao.GeneralDao
import ru.fevgenson.timetable.libraries.database.data.tables.SubjectEntity
import ru.fevgenson.timetable.shared.lesson.domain.entities.Lesson

@RunWith(AndroidJUnit4ClassRunner::class)
class DatabaseTest {

    private var lesson = Lesson(
        subject = "Math",
        time = "11:40-13:15",
        day = 1,
        weekType = 2
    )
    private lateinit var dao: GeneralDao
    private lateinit var database: LessonDatabase

    @Before
    fun createDB() {
        val context = InstrumentationRegistry.getInstrumentation().context
        database = Room.inMemoryDatabaseBuilder(context, LessonDatabase::class.java).build()
        dao = database.lessonDao()
    }

    @After
    fun closeDB() {
        database.close()
    }

    @Test
    fun commonInsetAndRead() {
        runBlocking {
            val subject = SubjectEntity(subject = "Math")
            val id = dao.insertSubject(subject = subject)
            val returnedSubjects = dao.getSubjects()

            assertEquals(subject.copy(id = id), returnedSubjects[0])
        }
    }

    @Test
    fun checkingInsertReturnedValue_OnConflict() {
        runBlocking {
            dao.insertSubject(SubjectEntity(subject = "Math"))
            val id2 = dao.insertSubject(SubjectEntity(subject = "Math"))
            val expectedId = -1L

            assertEquals(expectedId, id2)
        }
    }

    @Test
    fun checkingQuerySelectReturnedValue_OnNothingSelected() {
        runBlocking {
            val returnedSubject = dao.getSubject("Rus")

            assertNull(returnedSubject)
        }
    }

    @Test
    fun insertLesson() {
        runBlocking {
            val id = dao.insertLesson(lesson)
            val returnedLessonId = dao.getLesson(id).id

            assertEquals(id, returnedLessonId)
        }
    }

    @Test
    fun updateLesson() {
        runBlocking {
            val id = dao.insertLesson(lesson)
            val subject = lesson.subject
            lesson = lesson.copy(id = id, subject = "PE")
            dao.updateLesson(lesson)
            val updatedSubject = dao.getSubject(dao.getLesson(id).subject)

            assertNotEquals(subject, updatedSubject.subject)
        }
    }

    @Test
    fun getLessonForEdit() {
        runBlocking {
            lesson.id = dao.insertLesson(lesson)
            val returnedLesson = dao.getLessonForEdit(lesson.id)

            assertEquals(lesson, returnedLesson)
        }
    }

    @Test
    fun deleteRowInTable() {
        runBlocking {
            lesson.id = dao.insertLesson(lesson)
            dao.deleteLesson(lesson.id)
            val returnedLesson = dao.getLesson(lesson.id)

            assertNull(returnedLesson)
        }
    }

    @Test
    fun isRowsInAnotherTablesContinueToExist_WhenLessonTableWasDeleted() {
        runBlocking {
            lesson.id = dao.insertLesson(lesson)
            dao.deleteLesson(lesson.id)
            val stillExistingSubject = dao.getSubject(lesson.subject)

            assertNotNull(stillExistingSubject)
        }
    }

    @Test
    fun isLessonTableWillBeDeleted_WhenTheRowBeingReferenced_WillBeRemoved() {
        runBlocking {
            lesson.id = dao.insertLesson(lesson)
            dao.deleteAllSubjects()
            val isLessonStillExist = dao.getLesson(lesson.id)

            assertNull(isLessonStillExist)
        }
    }
}