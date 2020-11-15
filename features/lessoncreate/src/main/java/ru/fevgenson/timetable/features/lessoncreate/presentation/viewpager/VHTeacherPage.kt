package ru.fevgenson.timetable.features.lessoncreate.presentation.viewpager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.fevgenson.timetable.features.lessoncreate.R
import ru.fevgenson.timetable.features.lessoncreate.databinding.PageTeacherBinding
import ru.fevgenson.timetable.features.lessoncreate.presentation.LessonCreateViewModel
import ru.fevgenson.timetable.features.lessoncreate.presentation.flowbindingadapters.setupStartingSlots
import ru.fevgenson.timetable.features.lessoncreate.presentation.flowbindingadapters.setupTeacherData
import ru.fevgenson.timetable.libraries.flowbinding.nullableTextBind
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.slots.PredefinedSlots
import ru.tinkoff.decoro.watchers.MaskFormatWatcher

class VHTeacherPage private constructor(binding: PageTeacherBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {

        @ExperimentalCoroutinesApi
        fun from(
            parent: ViewGroup,
            viewModel: LessonCreateViewModel,
            coroutineScope: CoroutineScope
        ): VHTeacherPage {
            val inflater = LayoutInflater.from(parent.context)
            val binding = PageTeacherBinding.inflate(inflater, parent, false)
            with(binding) {
                with(nameEditText) {
                    setupTeacherData(viewModel.teacherAutocomplete, coroutineScope)
                    nullableTextBind(viewModel.teachersName, coroutineScope)
                }
                binding.emailEditText.nullableTextBind(viewModel.email, coroutineScope)
                with(phoneEditText) {
                    setupStartingSlots(R.string.lesson_create_value_starting_slots)
                    nullableTextBind(viewModel.phone, coroutineScope)
                    val mask = MaskImpl.createTerminated(PredefinedSlots.RUS_PHONE_NUMBER)
                    MaskFormatWatcher(mask).installOn(phoneEditText)
                }
                doneButton.setOnClickListener {
                    viewModel.onDoneButtonClick()
                }
                cancelButton.setOnClickListener {
                    viewModel.onCancelButtonClick()
                }
            }
            return VHTeacherPage(binding)
        }
    }
}