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
import ru.fevgenson.timetable.shared.lesson.data.dto.TimeDto

@Suppress("IllegalIdentifier")
@RunWith(AndroidJUnit4ClassRunner::class)
class TimeDaoTest {

    private companion object {

        const val TIME1 = "time1"
        const val TIME2 = "time2"
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
    fun insetAndReadTimes() {
        runBlocking {
            val time1 = TimeDto(time = TIME1)
            val time2 = TimeDto(time = TIME2)

            dao.insertTime(time1)
            dao.insertTime(time2)

            val time1WithId = time1.copy(id = 1)
            val time2WithId = time2.copy(id = 2)

            assertEquals(time1WithId, dao.getTime(1))
            assertEquals(time2WithId, dao.getTime(2))

            assertEquals(time1WithId, dao.getTime(TIME1))
            assertEquals(time2WithId, dao.getTime(TIME2))

            val allTimes = dao.getAllTimes()
            assertEquals(time1WithId, allTimes[0])
            assertEquals(time2WithId, allTimes[1])

            val allTimesFlow = dao.getAllTimesFlow().first()
            assertEquals(time1WithId, allTimesFlow[0])
            assertEquals(time2WithId, allTimesFlow[1])
        }
    }

    @Test
    fun deleteTimesById() {
        runBlocking {
            val time1 = TimeDto(time = TIME1)
            val time2 = TimeDto(time = TIME2)

            dao.insertTime(time1)
            dao.insertTime(time2)

            val time1WithId = time1.copy(id = 1)
            val time2WithId = time2.copy(id = 2)

            val allTimes = dao.getAllTimes()
            assertEquals(time1WithId, allTimes[0])
            assertEquals(time2WithId, allTimes[1])

            dao.deleteTime(1)
            val allTimesAfterFirstDelete = dao.getAllTimes()
            assertEquals(allTimesAfterFirstDelete.size, 1)
            assertEquals(time2WithId, allTimesAfterFirstDelete[0])

            dao.deleteTime(2)
            val allTimesAfterSecondDelete = dao.getAllTimes()
            assertEquals(allTimesAfterSecondDelete.size, 0)
        }
    }

    @Test
    fun deleteAllTimes() {
        runBlocking {
            val time1 = TimeDto(time = TIME1)
            val time2 = TimeDto(time = TIME2)

            dao.insertTime(time1)
            dao.insertTime(time2)

            val time1WithId = time1.copy(id = 1)
            val time2WithId = time2.copy(id = 2)

            val allTimes = dao.getAllTimes()
            assertEquals(time1WithId, allTimes[0])
            assertEquals(time2WithId, allTimes[1])

            dao.deleteAllTimes()
            val allTimesAfterDelete = dao.getAllTimes()
            assertEquals(allTimesAfterDelete.size, 0)
        }
    }

    @Test
    fun getTimeIdOrInsert() {
        runBlocking {
            val id = dao.getTimeIdOrInsert(TIME1)
            assertEquals(id, 1)
            assertEquals(dao.getAllTimes().size, 1)

            val secondCallId = dao.getTimeIdOrInsert(TIME1)
            assertEquals(secondCallId, id)
            assertEquals(dao.getAllTimes().size, 1)
        }
    }
}