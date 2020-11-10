package ru.fevgenson.timetable.shared.lesson.domain.usecase

import ru.fevgenson.timetable.shared.lesson.domain.entities.Lesson
import ru.fevgenson.timetable.shared.lesson.domain.repository.LessonRepository

class UpdateLessonUseCase(private val repository: LessonRepository) {

    suspend operator fun invoke(lessons: List<Lesson>) {
        repository.updateLesson(lessons.first())
        for (i in 1 until lessons.size) {
            repository.saveLesson(lessons[i].copy(id = 0))
        }
    }
}