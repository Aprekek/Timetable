package ru.fevgenson.timetable.shared.lesson.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.fevgenson.timetable.shared.lesson.data.dto.SubjectDto

@Dao
interface SubjectDao {

    suspend fun getSubjectIdOrInsert(subject: String): Long =
        getSubject(subject)?.id ?: insertSubject(SubjectDto(subject = subject))

    @Query("SELECT * from subject_table")
    suspend fun getAllSubjects(): List<SubjectDto>

    @Query("SELECT * from subject_table")
    fun getAllSubjectsFlow(): Flow<List<SubjectDto>>

    @Query("SELECT * from subject_table WHERE subject = :subject")
    suspend fun getSubject(subject: String): SubjectDto?

    @Query("SELECT * from subject_table WHERE subject_id = :id")
    suspend fun getSubject(id: Long): SubjectDto

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubject(subject: SubjectDto): Long

    @Query("DELETE from subject_table WHERE subject_id = :id")
    suspend fun deleteSubject(id: Long)

    @Query("DELETE from subject_table")
    suspend fun deleteAllSubjects()
}