package ru.fevgenson.timetable.features.dictionary.presentation.dictionary.viewpager

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import ru.fevgenson.timetable.features.dictionary.domain.Categories
import ru.fevgenson.timetable.features.dictionary.presentation.dictionary.DictionaryViewModel
import ru.fevgenson.timetable.shared.lesson.domain.usecase.*

class PageCategoryViewModel(
    val categoryType: Int,
    private val parentViewModel: DictionaryViewModel,
    getSubjectsValuesUseCaseFlow: GetSubjectsValuesUseCaseFlow,
    getClassroomsValuesUseCaseFlow: GetClassroomsValuesUseCaseFlow,
    getHousingsValuesUseCaseFlow: GetHousingsValuesUseCaseFlow,
    getTeachersUseCaseFlow: GetTeachersUseCaseFlow,
    getTimesValuesUseCaseFlow: GetTimesValuesUseCaseFlow
) : ViewModel() {

    val listCategoryItemsLiveData: LiveData<List<String>> = when (categoryType) {
        Categories.SUBJECT_CATEGORY -> {
            getSubjectsValuesUseCaseFlow()
                .flowOn(Dispatchers.IO).asLiveData(viewModelScope.coroutineContext)
        }
        Categories.CLASSROOM_CATEGORY -> {
            getClassroomsValuesUseCaseFlow()
                .flowOn(Dispatchers.IO).asLiveData(viewModelScope.coroutineContext)
        }
        Categories.HOUSING_CATEGORY -> {
            getHousingsValuesUseCaseFlow()
                .flowOn(Dispatchers.IO).asLiveData(viewModelScope.coroutineContext)
        }
        Categories.TEACHER_CATEGORY -> {
            getTeachersUseCaseFlow().map { list ->
                list.map { it.name }
            }.flowOn(Dispatchers.IO).asLiveData(viewModelScope.coroutineContext)
        }
        Categories.TIME_CATEGORY -> {
            getTimesValuesUseCaseFlow()
                .flowOn(Dispatchers.IO).asLiveData(viewModelScope.coroutineContext)
        }
        else -> throw IllegalStateException("$categoryType is not defined")
    }

    val isNoItemsTextVisible = Transformations.map(listCategoryItemsLiveData) {
        it.isNullOrEmpty()
    }

    fun onCategoryItemClick(categoryType: Int, categoryItem: String) {
        parentViewModel.onCategoryItemClick(categoryType, categoryItem)
    }
}