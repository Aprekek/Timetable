package ru.fevgenson.timetable.features.timetable.presentation.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class LessonViewHolderPool(parent: ViewGroup) : RecyclerView.RecycledViewPool() {

    companion object {
        const val MAX_PRE_CREATE_VIEW_HOLDERS = 56
    }

    init {
        setMaxRecycledViews(LessonViewHolder.VIEW_HOLDER_TYPE, Int.MAX_VALUE)
        for (i in 0..MAX_PRE_CREATE_VIEW_HOLDERS) {
            putRecycledView(LessonViewHolder.from(parent))
        }
    }
}