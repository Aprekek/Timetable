package ru.fevgenson.timetable.libraries.database.data.datasource

import kotlinx.coroutines.flow.Flow
import ru.fevgenson.timetable.libraries.database.data.generalDao.GeneralDao
import ru.fevgenson.timetable.libraries.database.data.tables.*

internal class FieldsDataSourceFlowImpl(private val dao: GeneralDao) : FieldsDataSourceFlow {

    override fun getAllSubjects(): Flow<List<SubjectEntity>> = dao.getSubjectsFlow()

    override fun getAllTimes(): Flow<List<TimeEntity>> = dao.getTimesFlow()

    override fun getAllHousings(): Flow<List<HousingEntity>> = dao.getHousingsFlow()

    override fun getAllClassrooms(): Flow<List<ClassroomEntity>> = dao.getClassroomsFlow()

    override fun getAllTypes(): Flow<List<TypeEntity>> = dao.getTypesFlow()

    override fun getAllTeachers(): Flow<List<TeacherEntity>> = dao.getTeachersFlow()
}