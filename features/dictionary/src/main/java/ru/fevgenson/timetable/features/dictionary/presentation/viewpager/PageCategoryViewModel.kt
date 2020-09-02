package ru.fevgenson.timetable.features.dictionary.presentation.viewpager

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import ru.fevgenson.timetable.features.dictionary.domain.Categories
import ru.fevgenson.timetable.features.dictionary.presentation.DictionaryViewModel

class PageCategoryViewModel(
    val categoryType: Int,
    private val parentViewModel: DictionaryViewModel
) : ViewModel() {

    //TODO переписать с использованием useCase
    val listCategoryItemsLiveData = liveData(Dispatchers.IO) {
        val listCategoryItems = when (categoryType) {
            Categories.SUBJECT_CATEGORY -> listOf(
                "math",
                "rus",
                "en"
            )
            Categories.TEACHER_CATEGORY -> listOf(
                "Hramova T.V"
            )
            else -> listOf(
                "11:40-13:15"
            )
        }
        emit(listCategoryItems)
    }

    fun onCategoryItemClick(categoryType: Int, categoryItem: String) {
        parentViewModel.onCategoryItemClick(categoryType, categoryItem)
    }
}