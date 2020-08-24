package ru.fevgenson.timetable.features.timetable.presentation.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.fevgenson.timetable.features.timetable.domain.entities.TimetableLesson
import ru.fevgenson.timetable.features.timetable.presentation.viewpager.PageDayViewModel

class LessonListAdapter(
    private val viewModel: PageDayViewModel
) : ListAdapter<TimetableLesson, LessonViewHolder>(LessonDiffUtils()) {

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LessonViewHolder = LessonViewHolder.from(parent)

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.bind(getItem(position), viewModel)
    }

    override fun getItemId(position: Int): Long = getItem(position).id

    override fun getItemViewType(position: Int): Int = LessonViewHolder.VIEW_HOLDER_TYPE
}

private class LessonDiffUtils : DiffUtil.ItemCallback<TimetableLesson>() {

    override fun areItemsTheSame(
        oldItem: TimetableLesson,
        newItem: TimetableLesson
    ): Boolean = newItem.time == oldItem.time

    override fun areContentsTheSame(
        oldItem: TimetableLesson,
        newItem: TimetableLesson
    ): Boolean = oldItem.subject == newItem.subject ||
            oldItem.time == newItem.time ||
            oldItem.type == newItem.type ||
            oldItem.housing == newItem.housing ||
            oldItem.classroom == newItem.classroom ||
            oldItem.teacher == newItem.teacher
}