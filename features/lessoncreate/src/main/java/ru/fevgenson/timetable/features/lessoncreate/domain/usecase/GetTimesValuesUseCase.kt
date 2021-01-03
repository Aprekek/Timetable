package ru.fevgenson.timetable.features.lessoncreate.domain.usecase

import ru.fevgenson.timetable.shared.lesson.domain.repository.TimeRepository

class GetTimesValuesUseCase(private val repository: TimeRepository) {

    suspend operator fun invoke(): List<String> = repository.getAllTimes().map { it.time }
}