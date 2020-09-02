package ru.fevgenson.timetable.features.dictionary.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.fevgenson.timetable.features.dictionary.presentation.DictionaryViewModel
import ru.fevgenson.timetable.features.dictionary.presentation.viewpager.ListOfLessonsByCategoryViewModel
import ru.fevgenson.timetable.features.dictionary.presentation.viewpager.PageCategoryViewModel

private val viewModelModule = module {
    viewModel {
        DictionaryViewModel()
    }
    viewModel { (categoryItem: String, categoryType: Int) ->
        ListOfLessonsByCategoryViewModel(categoryItem, categoryType)
    }

    factory { (categoryType: Int, parentViewModel: DictionaryViewModel) ->
        PageCategoryViewModel(
            categoryType,
            parentViewModel
        )
    }
}

val dictionaryListModules = listOf(viewModelModule)