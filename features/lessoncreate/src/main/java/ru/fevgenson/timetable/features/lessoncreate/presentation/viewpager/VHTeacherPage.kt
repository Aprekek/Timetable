package ru.fevgenson.timetable.features.lessoncreate.presentation.viewpager

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.fevgenson.timetable.features.lessoncreate.databinding.PageTeacherBinding
import ru.fevgenson.timetable.features.lessoncreate.presentation.LessonCreateViewModel

class VHTeacherPage private constructor(override val binding: PageTeacherBinding) :
    ViewHolder(binding) {

    companion object {
        fun from(parent: ViewGroup, viewModel: LessonCreateViewModel): VHTeacherPage {
            val page = VHTeacherPage(
                PageTeacherBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
            page.binding.lessonCreateViewModel = viewModel
            return page
        }
    }
}