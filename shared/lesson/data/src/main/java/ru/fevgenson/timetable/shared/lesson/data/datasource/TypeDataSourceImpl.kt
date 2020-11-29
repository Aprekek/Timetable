package ru.fevgenson.timetable.shared.lesson.data.datasource

import kotlinx.coroutines.flow.Flow
import ru.fevgenson.timetable.shared.lesson.data.dao.CommonLessonDao
import ru.fevgenson.timetable.shared.lesson.data.dto.TypeDto

class TypeDataSourceImpl(
    private val dao: CommonLessonDao
) : TypeDataSource {

    override suspend fun getType(id: Long): TypeDto = dao.getType(id)

    override suspend fun getAllTypes(): List<TypeDto> = dao.getAllTypes()

    override fun getAllTypesFlow(): Flow<List<TypeDto>> = dao.getAllTypesFlow()

    override suspend fun saveType(typeDto: TypeDto) {
        dao.insertType(typeDto)
    }

    override suspend fun deleteType(id: Long) {
        dao.deleteType(id)
    }

    override suspend fun deleteAllTypes() {
        dao.deleteAllTypes()
    }
}