package ru.fevgenson.timetable.features.dictionary.presentation.lessonsbycategory

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import ru.fevgenson.timetable.libraries.core.presentation.utils.eventutils.EventsDispatcher
import ru.fevgenson.timetable.shared.lesson.domain.entities.DomainTeacherEntity
import ru.fevgenson.timetable.shared.lesson.domain.entities.Lesson

class ListOfLessonsByCategoryViewModel(
    categoryItem: String,
    val categoryName: String,
    private val categoryType: Int
) : ViewModel() {

    interface EventsListener {
        fun navigateBack()
    }

    private val oldCategoryItem = categoryItem
    val realCategoryItem = MutableLiveData<String>(categoryItem)

    private var oldEmail: String? = null
    val realEmail = MutableLiveData<String>(null)
    private var oldPhone: String? = null
    val realPhone = MutableLiveData<String>(null)
    // TODO добавить переменные для работы со временью

    val lessons: LiveData<List<Lesson>> = getLessonsFromDatabase()
    val isNoItemsTextVisible = Transformations.map(lessons) {
        it.isNullOrEmpty()
    }

    val eventDispatcher = EventsDispatcher<EventsListener>()

    //TODO переписать с базой данных
    private fun getLessonsFromDatabase(): LiveData<List<Lesson>> {
        return liveData(Dispatchers.IO) {
            emit(
                listOf(
                    Lesson(
                        subject = "Programming",
                        time = "15:15-16:45",
                        day = 1,
                        weekType = 2,
                        teacher = DomainTeacherEntity(
                            name = "Fulman V.O",
                            phone = null,
                            email = null
                        )
                    ),
                    Lesson(
                        subject = "Programming",
                        time = "12:15-13:45",
                        day = 2,
                        weekType = 1
                    ),
                    Lesson(
                        subject = "Programming",
                        time = "12:15-13:45",
                        day = 2,
                        weekType = 1
                    ),
                    Lesson(
                        subject = "Programming",
                        time = "12:15-13:45",
                        day = 2,
                        weekType = 1
                    ),
                    Lesson(
                        subject = "Programming",
                        time = "12:15-13:45",
                        day = 2,
                        weekType = 1,
                        classroom = "A. 412",
                        housing = "K. 5",
                        type = "Practice"
                    ),
                    Lesson(
                        subject = "Programming",
                        time = "12:15-13:45",
                        day = 2,
                        weekType = 1
                    ),
                    Lesson(
                        subject = "Programming",
                        time = "12:15-13:45",
                        day = 2,
                        weekType = 1
                    )
                )
            )
        }
    }

    fun onBackButtonClick() {
        eventDispatcher.dispatchEvent { navigateBack() }
    }
}
