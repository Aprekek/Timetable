package ru.fevgenson.timetable.features.dictionary.presentation.dictionary

import androidx.lifecycle.ViewModel
import ru.fevgenson.timetable.features.dictionary.domain.Categories
import ru.fevgenson.timetable.features.dictionary.domain.scenario.GetListOfSubcategoriesScenario
import ru.fevgenson.timetable.features.dictionary.presentation.dictionary.viewpager.PageCategoryDelegate
import ru.fevgenson.timetable.libraries.core.presentation.utils.eventutils.EventsDispatcher

class DictionaryViewModel(
    getListOfSubcategoriesScenario: GetListOfSubcategoriesScenario
) : ViewModel() {

    interface EventListener {

        fun goToListOfLessonsByCategoryFragment(
            categoryType: Categories.CategoryTypes,
            categoryItem: String
        )
    }

    val eventsDispatcher = EventsDispatcher<EventListener>()

    val listOfPageCategoryViewModel = List(Categories.TOTAL_CATEGORIES) {
        PageCategoryDelegate(
            getListOfSubcategoriesScenario = getListOfSubcategoriesScenario,
            categoryType = Categories.CategoryTypes.values()[it],
            parentViewModel = this
        )
    }

    fun onCategoryItemClick(categoryType: Categories.CategoryTypes, categoryItem: String) {
        eventsDispatcher.dispatchEvent {
            goToListOfLessonsByCategoryFragment(
                categoryType,
                categoryItem
            )
        }
    }
}