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
import ru.fevgenson.timetable.libraries.database.data.dao.DaoImplementations
import ru.fevgenson.timetable.libraries.database.data.database.LessonDatabase
import ru.fevgenson.timetable.libraries.database.data.entities.SubjectEntity

@RunWith(AndroidJUnit4ClassRunner::class)
class DatabaseTest {

    private lateinit var dao: DaoImplementations
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
    fun insertAndRead() {
        runBlocking {
            val subject = SubjectEntity(subject = "Math")
            val id = dao.insertSubject(subject = subject)
            val returnedSubjects = dao.getSubjects()
            assertEquals(subject.copy(id = id), returnedSubjects[0])
        }
    }

    @Test
    fun check_InsertReturnedValue_OnConflict() {
        runBlocking {
            SubjectEntity(subject = "Math")
            val subject2 = SubjectEntity(subject = "Math")
            val id2 = dao.insertSubject(subject = subject2)

            val expectedId = 2
            assertEquals(id2, expectedId)
        }
    }

    @Test
    fun check_QuerySelectReturnedValue_OnNothingSelected() {
        runBlocking {
            val returnedSubject = dao.getSubject("Rus")
            assertNull(returnedSubject)
        }
    }

    @Test
    fun insertLesson() {
        val subject = "Rus"
        val time = "11:40-13:15"
        val day = 1
        val week = 2

        dao.insertLesson(
            Lesson(
                subject,
                time,
                day,
                week
            )
        )

        val dayEntity = dao.getDay(day)
        assertNotNull(dayEntity)
    }
}