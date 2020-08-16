package ru.fevgenson.timetable.features.timetable.presentation.viewpager

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.fevgenson.timetable.features.timetable.domain.entities.Lesson

class PageDayViewModel(
    private val currentDay: Int,
    private val currentWeekType: LiveData<Int>
) : ViewModel() {

    val lessonsLiveData = MutableLiveData<List<Lesson>>()

    init {
        viewModelScope.launch {
            lessonsLiveData.value = getMockList()
        }
    }

    private suspend fun getMockList() = with(Dispatchers.IO) {
        listOf(
            Lesson(
                id = 0,
                subject = "Математика",
                type = "Лекция",
                teacher = "Лыткина",
                classroom = "208a",
                day = 5,
                time = "14:00-15:30",
                housing = "Главный корпус",
                weekType = 0
            ),
            Lesson(
                id = 0,
                subject = "САОД",
                type = "Практика",
                teacher = "Турцев",
                classroom = "302",
                day = 5,
                time = "10:00-11:30",
                housing = "Новый корпус",
                weekType = 0
            ),
            Lesson(
                id = 0,
                subject = "Физра",
                type = "Практика",
                teacher = null,
                classroom = null,
                day = 5,
                time = "16:00-17:30",
                housing = "Старый корпус",
                weekType = 0
            ),
            Lesson(
                id = 0,
                subject = "Физика",
                type = null,
                teacher = "Дед",
                classroom = null,
                day = 5,
                time = "14:00-15:30",
                housing = "Главный корпус",
                weekType = 0
            ),
            Lesson(
                id = 0,
                subject = "Математика",
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
}