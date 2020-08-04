package ru.fevgenson.timetable.features.lessoncreate.presentation.viewpager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.fevgenson.timetable.features.lessoncreate.databinding.PageTeacherBinding
import ru.fevgenson.timetable.features.lessoncreate.presentation.LessonCreateViewModel
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.slots.PredefinedSlots
import ru.tinkoff.decoro.watchers.MaskFormatWatcher

class VHTeacherPage private constructor(private val binding: PageTeacherBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind() {
        val mask = MaskImpl.createTerminated(PredefinedSlots.RUS_PHONE_NUMBER)
        MaskFormatWatcher(mask).installOn(binding.phoneEditText)
    }

    companion object {
        fun from(parent: ViewGroup, viewModel: LessonCreateViewModel): VHTeacherPage {
            val inflater = LayoutInflater.from(parent.context)
            val binding = PageTeacherBinding.inflate(inflater, parent, false)
            binding.lessonCreateViewModel = viewModel
            return VHTeacherPage(binding)
        }
    }
}