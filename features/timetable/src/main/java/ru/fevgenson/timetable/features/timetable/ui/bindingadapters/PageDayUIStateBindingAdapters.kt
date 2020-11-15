package ru.fevgenson.timetable.features.timetable.ui.bindingadapters

import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.fevgenson.timetable.features.timetable.presentation.PageDayUIState

fun RecyclerView.observeUIState(
    uiStateFlow: Flow<PageDayUIState>,
    coroutineScope: CoroutineScope
) {
    uiStateFlow.onEach {
        isVisible = it is PageDayUIState.Content
    }.launchIn(coroutineScope)
}

fun ProgressBar.observeUIState(
    uiStateFlow: Flow<PageDayUIState>,
    coroutineScope: CoroutineScope
) {
    uiStateFlow.onEach {
        isVisible = it is PageDayUIState.Loading
    }.launchIn(coroutineScope)
}

fun TextView.observeUIState(
    uiStateFlow: Flow<PageDayUIState>,
    coroutineScope: CoroutineScope
) {
    uiStateFlow.onEach {
        isVisible = it is PageDayUIState.Empty
    }.launchIn(coroutineScope)
}