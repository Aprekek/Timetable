package ru.fevgenson.timetable.features.dictionary.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.fevgenson.timetable.features.dictionary.presentation.DictionaryViewModel
import ru.fevgenson.timetable.features.dictionary.presentation.viewpager.PageCategoryViewModel

private val viewModelModule = module {
    viewModel {
        DictionaryViewModel()
    }

    factory { (categoryType: Int) -> PageCategoryViewModel(categoryType) }
}

val dictionaryListModules = listOf(viewModelModule)