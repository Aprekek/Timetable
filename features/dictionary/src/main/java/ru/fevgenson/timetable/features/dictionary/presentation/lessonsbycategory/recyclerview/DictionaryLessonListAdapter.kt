package ru.fevgenson.timetable.features.dictionary.presentation.lessonsbycategory.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.fevgenson.timetable.features.dictionary.presentation.lessonsbycategory.ListOfLessonsByCategoryViewModel
import ru.fevgenson.timetable.libraries.database.domain.entities.Lesson

class DictionaryLessonListAdapter(
    private val viewModel: ListOfLessonsByCategoryViewModel
) : ListAdapter<Lesson, DictionaryLessonViewHolder>(LessonDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DictionaryLessonViewHolder =
        DictionaryLessonViewHolder.from(parent)

    override fun onBindViewHolder(holder: DictionaryLessonViewHolder, position: Int) {
        holder.bind(getItem(position), viewModel)
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