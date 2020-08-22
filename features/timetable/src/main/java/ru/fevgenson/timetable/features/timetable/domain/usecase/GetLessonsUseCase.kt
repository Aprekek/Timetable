package ru.fevgenson.timetable.features.timetable.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import ru.fevgenson.timetable.features.timetable.domain.entities.Lesson

class GetLessonsUseCase {
    //TODO: ждем бд
    operator fun invoke(weekType: Int, day: Int): LiveData<List<Lesson>> =
        liveData {
            emit(
                withContext(Dispatchers.IO) {
                    listOf(
                        Lesson(
                            id = 0,
                            subject = "Математика $weekType $day",
                            type = "Лекция",
                            teacher = "Лыткина",
                            classroom = "208a",
                            day = 5,
                            time = "14:00-15:30",
                            housing = "Главный корпус",
                            weekType = 0
                        ),
                        Lesson(
                            id = 1,
                            subject = "САОД $weekType $day",
                            type = "Практика",
                            teacher = "Турцев",
                            classroom = "302",
                            day = 5,
                            time = "10:00-11:30",
                            housing = "Новый корпус",
                            weekType = 0
                        ),
                        Lesson(
                            id = 2,
                            subject = "Физра $weekType $day",
                            type = null,
                            teacher = null,
                            classroom = null,
                            day = 5,
                            time = "16:00-17:30",
                            housing = null,
                            weekType = 0
                        ),
                        Lesson(
                            id = 3,
                            subject = "Физика $weekType $day",
                            type = null,
                            teacher = "Дед",
                            classroom = null,
                            day = 5,
                            time = "14:00-15:30",
                            housing = "Главный корпус",
                            weekType = 0
                        ),
                        Lesson(
                            id = 4,
                            subject = "Математика $weekType $day",
                            type = "Лекция",
                            teacher = "Лыткина",
                            classroom = "208a",
                            day = 5,
                            time = "09:00-8:30",
                            housing = "Главный корпус",
                            weekType = 0
                        )
                    ).apply { delay(500) }
                }
            )
        }
}