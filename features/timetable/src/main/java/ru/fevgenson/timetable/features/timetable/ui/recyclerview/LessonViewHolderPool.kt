package ru.fevgenson.timetable.features.timetable.ui.recyclerview

import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class LessonViewHolderPool : RecyclerView.RecycledViewPool() {

    init {
        setMaxRecycledViews(LessonViewHolder.VIEW_HOLDER_TYPE, Int.MAX_VALUE)
    }
}