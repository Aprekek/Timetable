package ru.fevgenson.timetable.features.lessoncreate.domain.usecase

import ru.fevgenson.timetable.shared.lesson.domain.repository.HousingRepository

class GetHousingsValuesUseCase(private val repository: HousingRepository) {

    suspend operator fun invoke(): List<String> = repository.getAllHousings().map { it.housing }
}