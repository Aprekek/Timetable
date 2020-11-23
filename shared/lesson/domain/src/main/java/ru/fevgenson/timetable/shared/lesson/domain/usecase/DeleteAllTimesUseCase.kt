package ru.fevgenson.timetable.shared.lesson.domain.usecase

import ru.fevgenson.timetable.shared.lesson.domain.repository.TimeRepository

class DeleteAllTimesUseCase(
    private val repository: TimeRepository
) {

    suspend operator fun invoke() {
        repository.deleteAllTimes()
    }
}