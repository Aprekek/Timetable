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
import ru.fevgenson.timetable.libraries.database.data.generalDao.GeneralDao
import ru.fevgenson.timetable.libraries.database.data.tables.SubjectEntity
import ru.fevgenson.timetable.libraries.database.database.LessonDatabase


@RunWith(AndroidJUnit4ClassRunner::class)
class DatabaseTest {

    private lateinit var generalDao: GeneralDao
    private lateinit var database: LessonDatabase

    @Before
    fun createDB() {
        val context = InstrumentationRegistry.getInstrumentation().context
        database = Room.inMemoryDatabaseBuilder(context, LessonDatabase::class.java).build()
        generalDao = database.lessonDao()
    }

    @After
    fun closeDB() {
        database.close()
    }

    @Test
    fun insertAndRead() {
        runBlocking {
            val subject = SubjectEntity(subject = "Math")
            val id = generalDao.insertSubject(subject = subject)
            val returnedSubjects = generalDao.getSubjects()
            assertEquals(subject.copy(id = id), returnedSubjects[0])
        }
    }

    @Test
    fun check_InsertReturnedValue_OnConflict() {
        runBlocking {
            SubjectEntity(subject = "Math")
            val subject2 = SubjectEntity(subject = "Math")
            val id2 = generalDao.insertSubject(subject = subject2)

            val expectedId = 2
            assertEquals(id2, expectedId)
        }
    }

    @Test
    fun check_QuerySelectReturnedValue_OnNothingSelected() {
        runBlocking {
            val returnedSubject = generalDao.getSubject("Rus")
            assertNull(returnedSubject)
        }
    }

    @Test
    fun insertLesson() {
        runBlocking {
            val subject = "Rus"
            val time = "11:40-13:15"
            val day = 1
            val week = 2
            val lesson = Lesson(
                subject = subject,
                time = time,
                day = day,
                weekType = week
            )

            val id = generalDao.insertLesson(lesson)
            val returnedLessonId = generalDao.getLesson(id).id

            assertEquals(id, returnedLessonId)
        }
    }

    @Test
    fun updateLesson() {
        runBlocking {
            val subject = "Rus"
            val time = "11:40-13:15"
            val day = 1
            val week = 2
            var lesson = Lesson(
                subject = subject,
                time = time,
                day = day,
                weekType = week
            )

            val id = generalDao.insertLesson(lesson)
            lesson = lesson.copy(id = id, subject = "PE")
            generalDao.updateLesson(lesson)
            val updatedSubject = generalDao.getSubject(generalDao.getLesson(id).subject)

            assertNotEquals(subject, updatedSubject.subject)
        }
    }

    @Test
    fun getLessonForEdit() {
        runBlocking {
            val subject = "Rus"
            val time = "11:40-13:15"
            val day = 1
            val week = 2
            val lesson = Lesson(
                subject = subject,
                time = time,
                day = day,
                weekType = week
            )

            lesson.id = generalDao.insertLesson(lesson)
            val returnedLesson = generalDao.getLessonForEdit(lesson.id)

            assertEquals(lesson, returnedLesson)
        }
    }
}