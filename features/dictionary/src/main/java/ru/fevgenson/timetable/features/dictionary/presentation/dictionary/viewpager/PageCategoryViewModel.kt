package ru.fevgenson.timetable.features.dictionary.presentation.dictionary.viewpager

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import ru.fevgenson.timetable.features.dictionary.domain.Categories
import ru.fevgenson.timetable.features.dictionary.presentation.dictionary.DictionaryViewModel

class PageCategoryViewModel(
    val categoryType: Int,
    private val parentViewModel: DictionaryViewModel
) : ViewModel() {

    //TODO (заглушка) переписать с использованием useCase
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
            Categories.CLASSROOM_CATEGORY -> listOf(
            )
            else -> listOf(
                "11:40-13:15"
            )
        }
        emit(listCategoryItems)
    }
    val isNoItemsTextVisible = Transformations.map(listCategoryItemsLiveData) {
        it.isNullOrEmpty()
    }

    fun onCategoryItemClick(categoryType: Int, categoryItem: String) {
        parentViewModel.onCategoryItemClick(categoryType, categoryItem)
    }
}