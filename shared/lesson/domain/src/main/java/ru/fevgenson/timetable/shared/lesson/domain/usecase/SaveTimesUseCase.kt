package ru.fevgenson.timetable.shared.lesson.domain.usecase

import ru.fevgenson.timetable.shared.lesson.domain.entity.TimeEntity
import ru.fevgenson.timetable.shared.lesson.domain.repository.TimeRepository

class SaveTimesUseCase(
    private val repository: TimeRepository
) {

    suspend operator fun invoke(vararg times: TimeEntity) {
        times.forEach {
            repository.saveTime(it)
        }
    }
}