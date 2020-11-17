package ru.fevgenson.timetable.features.timetable.ui.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.fevgenson.timetable.features.timetable.domain.entities.TimetableLesson
import ru.fevgenson.timetable.features.timetable.presentation.PageDayViewModelDelegate

@ExperimentalCoroutinesApi
class LessonListAdapter(
    private val viewModelDelegate: PageDayViewModelDelegate,
    private val coroutineScope: CoroutineScope
) : ListAdapter<TimetableLesson, LessonViewHolder>(LessonDiffUtils()) {

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LessonViewHolder = LessonViewHolder.from(parent)

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.bind(getItem(position), viewModelDelegate, coroutineScope)
    }

    override fun getItemViewType(position: Int): Int = LessonViewHolder.VIEW_HOLDER_TYPE

    override fun getItemId(position: Int): Long = getItem(position).position.toLong()
}

private class LessonDiffUtils : DiffUtil.ItemCallback<TimetableLesson>() {

    override fun areItemsTheSame(
        oldItem: TimetableLesson,
        newItem: TimetableLesson
    ): Boolean = newItem.position == oldItem.position

    override fun areContentsTheSame(
        oldItem: TimetableLesson,
        newItem: TimetableLesson
    ): Boolean = oldItem == newItem
}