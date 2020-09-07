package ru.fevgenson.timetable.features.timetable.presentation.recyclerview

import androidx.recyclerview.widget.RecyclerView

class LessonViewHolderPool : RecyclerView.RecycledViewPool() {

    init {
        setMaxRecycledViews(LessonViewHolder.VIEW_HOLDER_TYPE, Int.MAX_VALUE)
    }
}