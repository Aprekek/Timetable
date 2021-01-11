package ru.fevgenson.timetable.features.dictionary.presentation.dictionary.viewpager

import androidx.lifecycle.Transformations
import androidx.lifecycle.asLiveData
import ru.fevgenson.timetable.features.dictionary.domain.Categories
import ru.fevgenson.timetable.features.dictionary.domain.scenario.GetListOfSubcategoriesScenario
import ru.fevgenson.timetable.features.dictionary.presentation.dictionary.DictionaryViewModel

class PageCategoryDelegate(
    getListOfSubcategoriesScenario: GetListOfSubcategoriesScenario,
    val categoryType: Categories.CategoryTypes,
    private val parentViewModel: DictionaryViewModel
) {

    val listCategoryItemsLiveData =
        getListOfSubcategoriesScenario(categoryType).asLiveData()
    val isNoItemsTextVisible = Transformations.map(listCategoryItemsLiveData) {
        it.isNullOrEmpty()
    }

    fun onCategoryItemClick(categoryType: Categories.CategoryTypes, categoryItem: String) {
        parentViewModel.onCategoryItemClick(categoryType, categoryItem)
    }
}