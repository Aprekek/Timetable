package ru.fevgenson.timetable.features.lessoncreate.presentation.viewpager

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class ViewHolder(open val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {

//    abstract fun bind(viewModel: LessonCreateViewModel)
}