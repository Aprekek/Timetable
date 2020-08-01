package ru.fevgenson.timetable.features.lessoncreate.presentation.viewpager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.fevgenson.timetable.features.lessoncreate.databinding.PageMainBinding
import ru.fevgenson.timetable.features.lessoncreate.presentation.LessonCreateViewModel

class VHMainPage private constructor(binding: PageMainBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup, viewModel: LessonCreateViewModel): VHMainPage {
            val inflater = LayoutInflater.from(parent.context)
            val binding = PageMainBinding.inflate(inflater, parent, false)
            binding.lessonCreateViewModel = viewModel
            return VHMainPage(binding)
        }
    }
}