package ru.fevgenson.timetable.features.dictionary.presentation.dictionary.viewpager

import kotlinx.coroutines.flow.map
import ru.fevgenson.timetable.features.dictionary.R
import ru.fevgenson.timetable.features.dictionary.domain.Categories
import ru.fevgenson.timetable.features.dictionary.domain.scenario.GetListOfSubcategoriesScenario
import ru.fevgenson.timetable.features.dictionary.presentation.dictionary.DictionaryViewModel

class PageCategoryDelegate(
    getListOfSubcategoriesScenario: GetListOfSubcategoriesScenario,
    val categoryType: Categories.CategoryTypes,
    private val parentViewModel: DictionaryViewModel
) {

    val categoryItemsList = getListOfSubcategoriesScenario(categoryType)
    val isNoItemsTextVisible = categoryItemsList.map { list ->
        R.string.dictionary_no_items.takeIf { !list.isNullOrEmpty() }
    }

    fun onCategoryItemClick(categoryItem: String) {
        parentViewModel.onCategoryItemClick(categoryType, categoryItem)
    }
}