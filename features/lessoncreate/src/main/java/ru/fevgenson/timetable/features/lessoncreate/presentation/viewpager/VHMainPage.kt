package ru.fevgenson.timetable.features.lessoncreate.presentation.viewpager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import ru.fevgenson.timetable.features.lessoncreate.databinding.PageMainBinding
import ru.fevgenson.timetable.features.lessoncreate.presentation.LessonCreateViewModel

class VHMainPage private constructor(binding: PageMainBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(
            parent: ViewGroup,
            viewModel: LessonCreateViewModel,
            lifecycleOwner: LifecycleOwner
        ): VHMainPage {
            val inflater = LayoutInflater.from(parent.context)
            val binding = PageMainBinding.inflate(inflater, parent, false)
            binding.lifecycleOwner = lifecycleOwner
            binding.lessonCreateViewModel = viewModel
            return VHMainPage(binding)
        }
    }
}