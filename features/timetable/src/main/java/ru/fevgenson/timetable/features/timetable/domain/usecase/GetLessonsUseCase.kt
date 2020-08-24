package ru.fevgenson.timetable.features.timetable.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import ru.fevgenson.timetable.features.timetable.domain.entities.TimetableLesson
import ru.fevgenson.timetable.features.timetable.domain.entities.toTimetableLesson
import ru.fevgenson.timetable.libraries.database.domain.repository.LessonRepository

class GetLessonsUseCase(private val repository: LessonRepository) {

    operator fun invoke(
        weekType: Int,
        day: Int
    ): LiveData<List<TimetableLesson>> = liveData {
        withContext(Dispatchers.IO) {
            repository.getLessons(weekType, day).map { lessons ->
                lessons.map { it.toTimetableLesson() }
            }.collect {
                emit(it)
            }
        }
    }
}