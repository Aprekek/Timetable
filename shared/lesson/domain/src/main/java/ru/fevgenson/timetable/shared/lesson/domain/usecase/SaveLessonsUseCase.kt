package ru.fevgenson.timetable.shared.lesson.domain.usecase

import ru.fevgenson.timetable.shared.lesson.domain.entities.Lesson
import ru.fevgenson.timetable.shared.lesson.domain.repository.LessonRepository


class SaveLessonsUseCase(private val repository: LessonRepository) {

    suspend operator fun invoke(lessons: List<Lesson>) {
        lessons.forEach { repository.saveLesson(it) }
    }
}