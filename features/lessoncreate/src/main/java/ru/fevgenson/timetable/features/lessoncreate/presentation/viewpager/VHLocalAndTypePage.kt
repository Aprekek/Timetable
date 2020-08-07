package ru.fevgenson.timetable.features.lessoncreate.presentation.viewpager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import ru.fevgenson.timetable.features.lessoncreate.databinding.PageLocationAndTypeBinding
import ru.fevgenson.timetable.features.lessoncreate.presentation.LessonCreateViewModel

class VHLocalAndTypePage private constructor(binding: PageLocationAndTypeBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(
            parent: ViewGroup,
            viewModel: LessonCreateViewModel,
            lifecycleOwner: LifecycleOwner
        ): VHLocalAndTypePage {
            val inflater = LayoutInflater.from(parent.context)
            val binding = PageLocationAndTypeBinding.inflate(inflater, parent, false)
            binding.lifecycleOwner = lifecycleOwner
            binding.lessonCreateViewModel = viewModel
            return VHLocalAndTypePage(binding)
        }
    }
}