package ru.fevgenson.timetable.features.dictionary.presentation.dictionary

import androidx.lifecycle.ViewModel
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.get
import ru.fevgenson.timetable.features.dictionary.domain.Categories
import ru.fevgenson.timetable.features.dictionary.presentation.dictionary.viewpager.PageCategoryViewModel
import ru.fevgenson.timetable.libraries.core.presentation.utils.eventutils.EventsDispatcher

class DictionaryViewModel : ViewModel() {

    interface EventListener {

        fun goToListOfLessonsByCategoryFragment(
            categoryType: Categories.CategoryTypes,
            categoryItem: String
        )
    }

    val eventsDispatcher = EventsDispatcher<EventListener>()

    val listOfPageCategoryViewModel = List(Categories.TOTAL_CATEGORIES) { categoryType ->
        get(PageCategoryViewModel::class.java) {
            parametersOf(Categories.CategoryTypes.values()[categoryType], this)
        }
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