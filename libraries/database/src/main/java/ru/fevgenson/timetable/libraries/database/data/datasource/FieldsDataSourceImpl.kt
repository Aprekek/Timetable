package ru.fevgenson.timetable.libraries.database.data.datasource

import ru.fevgenson.timetable.libraries.database.data.generalDao.GeneralDao
import ru.fevgenson.timetable.libraries.database.data.tables.*

internal class FieldsDataSourceImpl(private val dao: GeneralDao) : FieldsDataSource {

    override suspend fun getAllSubjects(): List<SubjectEntity> = dao.getSubjects()

    override suspend fun getAllTimes(): List<TimeEntity> = dao.getTimes()

    override suspend fun getAllHousings(): List<HousingEntity> = dao.getHousings()

    override suspend fun getAllClassrooms(): List<ClassroomEntity> = dao.getClassrooms()

    override suspend fun getAllTypes(): List<TypeEntity> = dao.getTypes()

    override suspend fun getAllTeachers(): List<TeacherEntity> = dao.getTeachers()
}