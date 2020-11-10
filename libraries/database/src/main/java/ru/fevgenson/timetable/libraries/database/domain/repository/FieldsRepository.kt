package ru.fevgenson.timetable.libraries.database.domain.repository

import ru.fevgenson.timetable.shared.lesson.domain.entities.DomainTeacherEntity

interface FieldsRepository {

    suspend fun getAllSubjects(): List<String>
    suspend fun getAllTimes(): List<String>
    suspend fun getAllHousings(): List<String>
    suspend fun getAllClassrooms(): List<String>
    suspend fun getAllTypes(): List<String>
    suspend fun getAllTeachers(): List<DomainTeacherEntity>
}