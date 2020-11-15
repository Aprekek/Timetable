package ru.fevgenson.timetable.features.lessoncreate.presentation.viewpager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.fevgenson.timetable.features.lessoncreate.databinding.PageLocationAndTypeBinding
import ru.fevgenson.timetable.features.lessoncreate.presentation.LessonCreateViewModel
import ru.fevgenson.timetable.features.lessoncreate.presentation.flowbindingadapters.setupData
import ru.fevgenson.timetable.libraries.flowbinding.nullableTextBind

class VHLocalAndTypePage private constructor(binding: PageLocationAndTypeBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {

        @ExperimentalCoroutinesApi
        fun from(
            parent: ViewGroup,
            viewModel: LessonCreateViewModel,
            coroutineScope: CoroutineScope
        ): VHLocalAndTypePage {
            val inflater = LayoutInflater.from(parent.context)
            val binding = PageLocationAndTypeBinding.inflate(inflater, parent, false)
            with(binding) {
                with(includeLessonCreateButtons) {
                    nextButton.setOnClickListener {
                        viewModel.onNextButtonClick()
                    }
                    doneButton.setOnClickListener {
                        viewModel.onDoneButtonClick()
                    }
                    cancelButton.setOnClickListener {
                        viewModel.onCancelButtonClick()
                    }
                }
                with(housingEditText) {
                    nullableTextBind(viewModel.housing, coroutineScope)
                    setupData(viewModel.housingAutoComplete, coroutineScope)
                }
                with(classroomEditText) {
                    nullableTextBind(viewModel.classroom, coroutineScope)
                    setupData(viewModel.classroomAutoComplete, coroutineScope)
                }
                with(typeEditText) {
                    nullableTextBind(viewModel.type, coroutineScope)
                    setupData(viewModel.typeAutocomplete, coroutineScope)
                }
            }
            return VHLocalAndTypePage(binding)
        }
    }
}