package ru.fevgenson.timetable.features.timetable.presentation.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.fevgenson.timetable.features.timetable.databinding.ItemLessonBinding
import ru.fevgenson.timetable.features.timetable.domain.entities.Lesson
import ru.fevgenson.timetable.features.timetable.presentation.viewpager.PageDayViewModel

class LessonViewHolder private constructor(
    private val binding: ItemLessonBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {

        fun from(
            parent: ViewGroup,
            viewModel: PageDayViewModel
        ): LessonViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemLessonBinding.inflate(inflater, parent, false)
            binding.viewModel = viewModel
            return LessonViewHolder(binding)
        }
    }

    fun bind(lesson: Lesson) {
        binding.lesson = lesson
    }
}