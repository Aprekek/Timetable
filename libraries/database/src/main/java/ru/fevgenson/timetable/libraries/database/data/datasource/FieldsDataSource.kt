package ru.fevgenson.timetable.libraries.database.data.datasource

import ru.fevgenson.timetable.libraries.database.data.tables.*

internal interface FieldsDataSource {

    suspend fun getAllSubjects(): List<SubjectEntity>
    suspend fun getAllTimes(): List<TimeEntity>
    suspend fun getAllHousings(): List<HousingEntity>
    suspend fun getAllClassrooms(): List<ClassroomEntity>
    suspend fun getAllTypes(): List<TypeEntity>
    suspend fun getAllTeachers(): List<TeacherEntity>
}