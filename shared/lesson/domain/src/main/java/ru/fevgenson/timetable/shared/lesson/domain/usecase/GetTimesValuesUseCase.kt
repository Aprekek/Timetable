package ru.fevgenson.timetable.shared.lesson.domain.usecase

import ru.fevgenson.timetable.shared.lesson.domain.repository.FieldsRepository

class GetTimesValuesUseCase(private val repository: FieldsRepository) {

    suspend operator fun invoke(): List<String> = repository.getAllTimes()
}