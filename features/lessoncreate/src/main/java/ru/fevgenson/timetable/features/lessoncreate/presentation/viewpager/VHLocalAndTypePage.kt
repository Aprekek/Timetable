package ru.fevgenson.timetable.features.lessoncreate.presentation.viewpager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
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
            lifecycleOwner: LifecycleOwner
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
                    nullableTextBind(viewModel.housing, lifecycleOwner)
                    setupData(viewModel.housingAutoComplete, lifecycleOwner)
                }
                with(classroomEditText) {
                    nullableTextBind(viewModel.classroom, lifecycleOwner)
                    setupData(viewModel.classroomAutoComplete, lifecycleOwner)
                }
                with(typeEditText) {
                    nullableTextBind(viewModel.type, lifecycleOwner)
                    setupData(viewModel.typeAutocomplete, lifecycleOwner)
                }
            }
            return VHLocalAndTypePage(binding)
        }
    }
}