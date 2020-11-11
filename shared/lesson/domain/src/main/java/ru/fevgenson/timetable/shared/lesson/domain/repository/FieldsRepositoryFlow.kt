package ru.fevgenson.timetable.shared.lesson.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.fevgenson.timetable.shared.lesson.domain.entities.DomainTeacherEntity

interface FieldsRepositoryFlow {

    fun getAllSubjects(): Flow<List<String>>
    fun getAllTimes(): Flow<List<String>>
    fun getAllHousings(): Flow<List<String>>
    fun getAllClassrooms(): Flow<List<String>>
    fun getAllTypes(): Flow<List<String>>
    fun getAllTeachers(): Flow<List<DomainTeacherEntity>>
}