package ru.fevgenson.timetable.features.timetable.presentation.recyclerview

import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.fevgenson.timetable.features.timetable.domain.entities.TimetableLesson
import ru.fevgenson.timetable.features.timetable.presentation.viewpager.PageDayViewModel

class LessonListAdapter(
    private val viewModel: PageDayViewModel,
    private val lifecycleOwner: LifecycleOwner
) : ListAdapter<TimetableLesson, LessonViewHolder>(LessonDiffUtils()) {

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LessonViewHolder = LessonViewHolder.from(parent)

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.bind(getItem(position), viewModel, lifecycleOwner)
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