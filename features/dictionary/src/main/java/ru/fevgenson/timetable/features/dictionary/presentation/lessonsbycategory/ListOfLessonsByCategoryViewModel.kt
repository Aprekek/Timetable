package ru.fevgenson.timetable.features.dictionary.presentation.lessonsbycategory

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.flow.map
import ru.fevgenson.timetable.libraries.core.presentation.utils.eventutils.EventsDispatcher
import ru.fevgenson.timetable.shared.lesson.domain.entities.Lesson
import ru.fevgenson.timetable.shared.lesson.domain.usecase.GetLessonsBySubcategoryUseCase

class ListOfLessonsByCategoryViewModel(
    subcategoryName: String,
    val categoryName: String,
    private val categoryType: Int,
    getLessonsBySubcategoryUseCase: GetLessonsBySubcategoryUseCase
) : ViewModel() {

    interface EventsListener {
        fun navigateBack()
    }

    init {
        Log.d("viewModel", "subcategoryName $subcategoryName")
        Log.d("viewModel", "categoryName $categoryName")
        Log.d("viewModel", "categoryType $categoryType")
    }

    private val oldCategoryItem = subcategoryName
    val realCategoryItem = MutableLiveData<String>(subcategoryName)

    private var oldEmail: String? = null
    val realEmail = MutableLiveData<String>(null)
    private var oldPhone: String? = null
    val realPhone = MutableLiveData<String>(null)
    // TODO добавить переменные для работы со временью

    val lessons: LiveData<List<Lesson>> =
        getLessonsBySubcategoryUseCase(
            categoryType,
            subcategoryName
        ).map { list ->
            list.map {
                Log.d("DBG $subcategoryName", " $it")
                it
            }
        }.asLiveData(viewModelScope.coroutineContext)

    val isNoItemsTextVisible = Transformations.map(lessons) {
        it.isNullOrEmpty()
    }

    val eventDispatcher = EventsDispatcher<EventsListener>()

    fun onBackButtonClick() {
        eventDispatcher.dispatchEvent { navigateBack() }
    }
}
