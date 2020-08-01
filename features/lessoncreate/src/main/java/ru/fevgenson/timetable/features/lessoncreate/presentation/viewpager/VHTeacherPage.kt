package ru.fevgenson.timetable.features.lessoncreate.presentation.viewpager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.fevgenson.timetable.features.lessoncreate.databinding.PageTeacherBinding
import ru.fevgenson.timetable.features.lessoncreate.presentation.LessonCreateViewModel

class VHTeacherPage private constructor(binding: PageTeacherBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup, viewModel: LessonCreateViewModel): VHTeacherPage {
            val inflater = LayoutInflater.from(parent.context)
            val binding = PageTeacherBinding.inflate(inflater, parent, false)
            binding.lessonCreateViewModel = viewModel
            return VHTeacherPage(binding)
        }
    }
}