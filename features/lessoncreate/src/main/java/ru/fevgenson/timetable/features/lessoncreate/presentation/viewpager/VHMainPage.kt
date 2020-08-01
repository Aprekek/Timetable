package ru.fevgenson.timetable.features.lessoncreate.presentation.viewpager

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.fevgenson.timetable.features.lessoncreate.databinding.PageMainBinding
import ru.fevgenson.timetable.features.lessoncreate.presentation.LessonCreateViewModel

class VHMainPage private constructor(override val binding: PageMainBinding) :
    ViewHolder(binding) {

    companion object {
        fun from(parent: ViewGroup, viewModel: LessonCreateViewModel): VHMainPage {
            val page = VHMainPage(
                PageMainBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
            page.binding.lessonCreateViewModel = viewModel
            return page
        }
    }
}