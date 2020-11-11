package ru.fevgenson.timetable.libraries.database.data.datasource

import kotlinx.coroutines.flow.Flow
import ru.fevgenson.timetable.libraries.database.data.tables.*

internal interface FieldsDataSourceFlow {

    fun getAllSubjects(): Flow<List<SubjectEntity>>
    fun getAllTimes(): Flow<List<TimeEntity>>
    fun getAllHousings(): Flow<List<HousingEntity>>
    fun getAllClassrooms(): Flow<List<ClassroomEntity>>
    fun getAllTypes(): Flow<List<TypeEntity>>
    fun getAllTeachers(): Flow<List<TeacherEntity>>
}