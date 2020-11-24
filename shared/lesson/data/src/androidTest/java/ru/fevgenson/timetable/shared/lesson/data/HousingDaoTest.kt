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
import ru.fevgenson.timetable.shared.lesson.data.dto.HousingDto

@Suppress("IllegalIdentifier")
@RunWith(AndroidJUnit4ClassRunner::class)
class HousingDaoTest {

    private companion object {

        const val HOUSING1 = "housing1"
        const val HOUSING2 = "housing2"
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
    fun insetAndReadHousings() {
        runBlocking {
            val housing1 = HousingDto(housing = HOUSING1)
            val housing2 = HousingDto(housing = HOUSING2)

            dao.insertHousing(housing1)
            dao.insertHousing(housing2)

            val housing1WithId = housing1.copy(id = 1)
            val housing2WithId = housing2.copy(id = 2)

            assertEquals(housing1WithId, dao.getHousing(1))
            assertEquals(housing2WithId, dao.getHousing(2))

            assertEquals(housing1WithId, dao.getHousing(HOUSING1))
            assertEquals(housing2WithId, dao.getHousing(HOUSING2))

            val allHousings = dao.getAllHousings()
            assertEquals(housing1WithId, allHousings[0])
            assertEquals(housing2WithId, allHousings[1])

            val allHousingsFlow = dao.getAllHousingsFlow().first()
            assertEquals(housing1WithId, allHousingsFlow[0])
            assertEquals(housing2WithId, allHousingsFlow[1])
        }
    }

    @Test
    fun deleteHousingsById() {
        runBlocking {
            val housing1 = HousingDto(housing = HOUSING1)
            val housing2 = HousingDto(housing = HOUSING2)

            dao.insertHousing(housing1)
            dao.insertHousing(housing2)

            val housing1WithId = housing1.copy(id = 1)
            val housing2WithId = housing2.copy(id = 2)

            val allHousings = dao.getAllHousings()
            assertEquals(housing1WithId, allHousings[0])
            assertEquals(housing2WithId, allHousings[1])

            dao.deleteHousing(1)
            val allHousingsAfterFirstDelete = dao.getAllHousings()
            assertEquals(allHousingsAfterFirstDelete.size, 1)
            assertEquals(housing2WithId, allHousingsAfterFirstDelete[0])

            dao.deleteHousing(2)
            val allHousingsAfterSecondDelete = dao.getAllHousings()
            assertEquals(allHousingsAfterSecondDelete.size, 0)
        }
    }

    @Test
    fun deleteAllHousings() {
        runBlocking {
            val housing1 = HousingDto(housing = HOUSING1)
            val housing2 = HousingDto(housing = HOUSING2)

            dao.insertHousing(housing1)
            dao.insertHousing(housing2)

            val housing1WithId = housing1.copy(id = 1)
            val housing2WithId = housing2.copy(id = 2)

            val allHousings = dao.getAllHousings()
            assertEquals(housing1WithId, allHousings[0])
            assertEquals(housing2WithId, allHousings[1])

            dao.deleteAllHousings()
            val allHousingsAfterDelete = dao.getAllHousings()
            assertEquals(allHousingsAfterDelete.size, 0)
        }
    }

    @Test
    fun getHousingIdOrInsert() {
        runBlocking {
            val id = dao.getHousingIdOrInsert(HOUSING1)
            assertEquals(id, 1)
            assertEquals(dao.getAllHousings().size, 1)

            val secondCallId = dao.getHousingIdOrInsert(HOUSING1)
            assertEquals(secondCallId, id)
            assertEquals(dao.getAllHousings().size, 1)
        }
    }
}