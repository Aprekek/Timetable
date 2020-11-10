package ru.fevgenson.timetable.features.dictionary.presentation.lessonsbycategory.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.fevgenson.timetable.features.dictionary.databinding.LessonItemDictionaryBinding
import ru.fevgenson.timetable.features.dictionary.presentation.lessonsbycategory.ListOfLessonsByCategoryViewModel
import ru.fevgenson.timetable.shared.lesson.domain.entities.Lesson

class DictionaryLessonViewHolder private constructor(
    private val binding: LessonItemDictionaryBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup): DictionaryLessonViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = LessonItemDictionaryBinding.inflate(inflater, parent, false)
            return DictionaryLessonViewHolder(binding)
        }
    }

    fun bind(lesson: Lesson, viewModel: ListOfLessonsByCategoryViewModel) {
        binding.lesson = lesson
        binding.viewModel = viewModel
    }
}