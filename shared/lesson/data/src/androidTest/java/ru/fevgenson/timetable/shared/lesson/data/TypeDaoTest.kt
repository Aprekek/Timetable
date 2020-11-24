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
import ru.fevgenson.timetable.shared.lesson.data.dto.TypeDto

@Suppress("IllegalIdentifier")
@RunWith(AndroidJUnit4ClassRunner::class)
class TypeDaoTest {

    private companion object {

        const val TYPE1 = "type1"
        const val TYPE2 = "type2"
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
    fun insetAndReadTypes() {
        runBlocking {
            val type1 = TypeDto(type = TYPE1)
            val type2 = TypeDto(type = TYPE2)

            dao.insertType(type1)
            dao.insertType(type2)

            val type1WithId = type1.copy(id = 1)
            val type2WithId = type2.copy(id = 2)

            assertEquals(type1WithId, dao.getType(1))
            assertEquals(type2WithId, dao.getType(2))

            assertEquals(type1WithId, dao.getType(TYPE1))
            assertEquals(type2WithId, dao.getType(TYPE2))

            val allTypes = dao.getAllTypes()
            assertEquals(type1WithId, allTypes[0])
            assertEquals(type2WithId, allTypes[1])

            val allTypesFlow = dao.getAllTypesFlow().first()
            assertEquals(type1WithId, allTypesFlow[0])
            assertEquals(type2WithId, allTypesFlow[1])
        }
    }

    @Test
    fun deleteTypesById() {
        runBlocking {
            val type1 = TypeDto(type = TYPE1)
            val type2 = TypeDto(type = TYPE2)

            dao.insertType(type1)
            dao.insertType(type2)

            val type1WithId = type1.copy(id = 1)
            val type2WithId = type2.copy(id = 2)

            val allTypes = dao.getAllTypes()
            assertEquals(type1WithId, allTypes[0])
            assertEquals(type2WithId, allTypes[1])

            dao.deleteType(1)
            val allTypesAfterFirstDelete = dao.getAllTypes()
            assertEquals(allTypesAfterFirstDelete.size, 1)
            assertEquals(type2WithId, allTypesAfterFirstDelete[0])

            dao.deleteType(2)
            val allTypesAfterSecondDelete = dao.getAllTypes()
            assertEquals(allTypesAfterSecondDelete.size, 0)
        }
    }

    @Test
    fun deleteAllTypes() {
        runBlocking {
            val type1 = TypeDto(type = TYPE1)
            val type2 = TypeDto(type = TYPE2)

            dao.insertType(type1)
            dao.insertType(type2)

            val type1WithId = type1.copy(id = 1)
            val type2WithId = type2.copy(id = 2)

            val allTypes = dao.getAllTypes()
            assertEquals(type1WithId, allTypes[0])
            assertEquals(type2WithId, allTypes[1])

            dao.deleteAllTypes()
            val allTypesAfterDelete = dao.getAllTypes()
            assertEquals(allTypesAfterDelete.size, 0)
        }
    }

    @Test
    fun getTypeIdOrInsert() {
        runBlocking {
            val id = dao.getTypeIdOrInsert(TYPE1)
            assertEquals(id, 1)
            assertEquals(dao.getAllTypes().size, 1)

            val secondCallId = dao.getTypeIdOrInsert(TYPE1)
            assertEquals(secondCallId, id)
            assertEquals(dao.getAllTypes().size, 1)
        }
    }
}