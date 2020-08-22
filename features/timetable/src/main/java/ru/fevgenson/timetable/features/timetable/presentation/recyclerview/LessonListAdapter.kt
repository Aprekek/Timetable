package ru.fevgenson.timetable.features.timetable.presentation.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.fevgenson.timetable.features.timetable.domain.entities.Lesson

class LessonListAdapter : ListAdapter<Lesson, LessonViewHolder>(LessonDiffUtils()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LessonViewHolder = LessonViewHolder.from(parent)

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

private class LessonDiffUtils : DiffUtil.ItemCallback<Lesson>() {

    override fun areItemsTheSame(
        oldItem: Lesson,
        newItem: Lesson
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Lesson,
        newItem: Lesson
    ): Boolean = oldItem == newItem
}