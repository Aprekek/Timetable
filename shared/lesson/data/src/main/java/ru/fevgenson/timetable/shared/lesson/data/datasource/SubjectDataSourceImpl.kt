package ru.fevgenson.timetable.shared.lesson.data.datasource

import kotlinx.coroutines.flow.Flow
import ru.fevgenson.timetable.shared.lesson.data.dao.CommonLessonDao
import ru.fevgenson.timetable.shared.lesson.data.dto.SubjectDto

class SubjectDataSourceImpl(
    private val dao: CommonLessonDao
) : SubjectDataSource {

    override suspend fun getSubject(id: Long): SubjectDto = dao.getSubject(id)

    override suspend fun getAllSubjects(): List<SubjectDto> = dao.getAllSubjects()

    override fun getAllSubjectsFlow(): Flow<List<SubjectDto>> = dao.getAllSubjectsFlow()

    override suspend fun saveSubject(subjectDto: SubjectDto) {
        dao.insertSubject(subjectDto)
    }

    override suspend fun deleteSubject(id: Long) {
        dao.deleteSubject(id)
    }

    override suspend fun deleteAllSubjects() {
        dao.deleteAllSubjects()
    }
}