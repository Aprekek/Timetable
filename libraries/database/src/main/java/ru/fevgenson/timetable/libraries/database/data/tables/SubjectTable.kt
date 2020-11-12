package ru.fevgenson.timetable.libraries.database.data.tables

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "subject_table", indices = [Index(value = ["subject"], unique = true)])
data class SubjectEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val subject: String
)

data class SubjectLessonRelations(
    var id: Long,

    @Relation(parentColumn = "id", entityColumn = "subject")
    var lessons: List<LessonEntity>
)

@Dao
internal interface SubjectDao {

    @Transaction
    @Query("SELECT id from subject_table WHERE subject =:subject")
    fun getLessonsBySubject(subject: String): Flow<SubjectLessonRelations>

    @Query("SELECT * from subject_table")
    suspend fun getSubjects(): List<SubjectEntity>

    @Query("SELECT * from subject_table")
    fun getSubjectsFlow(): Flow<List<SubjectEntity>>

    @Query("SELECT * from subject_table WHERE subject = :subject")
    suspend fun getSubject(subject: String): SubjectEntity?

    @Query("SELECT * from subject_table WHERE id = :id")
    suspend fun getSubject(id: Long): SubjectEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSubject(subject: SubjectEntity): Long

    @Update
    suspend fun updateSubject(subject: SubjectEntity)

    @Delete
    suspend fun deleteSubject(subject: SubjectEntity)

    @Query("DELETE from subject_table WHERE id = :id")
    suspend fun deleteSubject(id: Long)

    @Query("DELETE from subject_table")
    suspend fun deleteAllSubjects()
}