package ru.fevgenson.timetable.features.lessoncreate.presentation.viewpager

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.fevgenson.timetable.features.lessoncreate.databinding.PageLocationAndTypeBinding
import ru.fevgenson.timetable.features.lessoncreate.presentation.LessonCreateViewModel

class VHLocalAndTypePage private constructor(override val binding: PageLocationAndTypeBinding) :
    ViewHolder(binding) {

    companion object {
        fun from(parent: ViewGroup, viewModel: LessonCreateViewModel): VHLocalAndTypePage {
            val page = VHLocalAndTypePage(
                PageLocationAndTypeBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
            page.binding.lessonCreateViewModel = viewModel
            return page
        }
    }
}