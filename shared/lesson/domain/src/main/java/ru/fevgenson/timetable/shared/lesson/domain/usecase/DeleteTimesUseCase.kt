package ru.fevgenson.timetable.shared.lesson.domain.usecase

import ru.fevgenson.timetable.shared.lesson.domain.repository.TimeRepository

class DeleteTimesUseCase(
    private val repository: TimeRepository
) {

    suspend operator fun invoke(vararg ids: Long) {
        ids.forEach {
            repository.deleteTime(it)
        }
    }
}