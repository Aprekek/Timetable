package ru.fevgenson.timetable.shared.lesson.domain.usecase

import ru.fevgenson.timetable.shared.lesson.domain.repository.ClassroomRepository

class DeleteAllClassroomsUseCase(
    private val repository: ClassroomRepository
) {

    suspend operator fun invoke() {
        repository.deleteAllClassrooms()
    }
}