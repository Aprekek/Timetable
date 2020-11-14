package ru.fevgenson.timetable.features.lessoncreate.presentation.viewpager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.fevgenson.timetable.features.lessoncreate.databinding.PageMainBinding
import ru.fevgenson.timetable.features.lessoncreate.presentation.LessonCreateViewModel
import ru.fevgenson.timetable.features.lessoncreate.presentation.flowbindingadapters.bindChips
import ru.fevgenson.timetable.features.lessoncreate.presentation.flowbindingadapters.setAutocompleteState
import ru.fevgenson.timetable.features.lessoncreate.presentation.flowbindingadapters.setupData
import ru.fevgenson.timetable.libraries.core.utils.dateutils.MyTimeUtils
import ru.fevgenson.timetable.libraries.flowbinding.nullableTextBind
import ru.fevgenson.timetable.libraries.flowbinding.showError
import ru.fevgenson.timetable.libraries.flowbinding.textBind

class VHMainPage private constructor(binding: PageMainBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {

        @ExperimentalCoroutinesApi
        fun from(
            parent: ViewGroup,
            viewModel: LessonCreateViewModel,
            lifecycleOwner: LifecycleOwner
        ): VHMainPage {
            val inflater = LayoutInflater.from(parent.context)
            val binding = PageMainBinding.inflate(inflater, parent, false)
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
                binding.subjectTextInputLayout.showError(viewModel.subjectError, lifecycleOwner)
                with(subjectEditText) {
                    nullableTextBind(viewModel.subject, lifecycleOwner)
                    setupData(viewModel.subjectAutoComplete, lifecycleOwner)
                }
                with(buttonTimeStart) {
                    setOnClickListener {
                        viewModel.onTimeSetButtonClick(MyTimeUtils.TimeBorders.START)
                    }
                    textBind(viewModel.timeStartString, lifecycleOwner)
                }
                with(buttonTimeEnd) {
                    setOnClickListener {
                        viewModel.onTimeSetButtonClick(MyTimeUtils.TimeBorders.END)
                    }
                    textBind(viewModel.timeEndString, lifecycleOwner)
                }
                with(pickTimeButton) {
                    setOnClickListener {
                        viewModel.onAutocompleteTimeButtonClick()
                    }
                    setAutocompleteState(viewModel.timeAutoComplete, lifecycleOwner)
                }
                firstWeekChips.chipGroup.bindChips(viewModel.firstWeekChips, lifecycleOwner)
                secondWeekChips.chipGroup.bindChips(viewModel.secondWeekChips, lifecycleOwner)
            }
            return VHMainPage(binding)
        }
    }
}