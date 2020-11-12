package ru.fevgenson.timetable.shared.lesson.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import ru.fevgenson.timetable.shared.lesson.domain.entities.Lesson
import ru.fevgenson.timetable.shared.lesson.domain.repository.LessonRepository

class GetLessonsBySubcategoryUseCase(private val repository: LessonRepository) {

    operator fun invoke(
        category: Int,
        subcategoryName: String
    ): Flow<List<Lesson>> = repository.getLessonsBySubcategory(category, subcategoryName)
        .flowOn(Dispatchers.IO)
}