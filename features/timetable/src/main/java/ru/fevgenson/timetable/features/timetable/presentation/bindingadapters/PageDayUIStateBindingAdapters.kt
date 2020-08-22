package ru.fevgenson.timetable.features.timetable.presentation.bindingadapters

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.fevgenson.timetable.features.timetable.presentation.viewpager.PageDayUIState

@BindingAdapter("uiState")
fun RecyclerView.setUIState(uiState: PageDayUIState) {
    visibility = when (uiState) {
        is PageDayUIState.Content -> View.VISIBLE
        else -> View.GONE
    }
}

@BindingAdapter("firstWeekUIState", "secondWeekUIState")
fun ProgressBar.setUIState(
    firstWeekUiState: PageDayUIState,
    secondWeekUIState: PageDayUIState
) {
    visibility = when {
        secondWeekUIState is PageDayUIState.Loading ||
                firstWeekUiState is PageDayUIState.Loading -> View.VISIBLE
        else -> View.GONE
    }
}

@BindingAdapter("firstWeekUIState", "secondWeekUIState")
fun TextView.setUIState(
    firstWeekUiState: PageDayUIState,
    secondWeekUIState: PageDayUIState
) {
    visibility = when {
        firstWeekUiState is PageDayUIState.Empty ||
                secondWeekUIState is PageDayUIState.Empty -> View.VISIBLE
        else -> View.GONE
    }
}