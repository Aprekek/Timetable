package ru.fevgenson.timetable.features.dictionary.presentation

import androidx.lifecycle.ViewModel
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.get
import ru.fevgenson.timetable.features.dictionary.domain.Categories
import ru.fevgenson.timetable.features.dictionary.presentation.viewpager.PageCategoryViewModel

class DictionaryViewModel : ViewModel() {

    val listOfPageCategoryViewModel = List(Categories.TOTAL_CATEGORIES) { categoryType ->
        get(PageCategoryViewModel::class.java) {
            parametersOf(categoryType)
        }
    }
}