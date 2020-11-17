package ru.fevgenson.timetable.features.lessoncreate.presentation

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import ru.fevgenson.libraries.navigation.di.NavigationConstants
import ru.fevgenson.timetable.features.lessoncreate.R
import ru.fevgenson.timetable.features.lessoncreate.databinding.FragmentLessonCreateBinding
import ru.fevgenson.timetable.features.lessoncreate.presentation.flowbindingadapters.initMenu
import ru.fevgenson.timetable.features.lessoncreate.presentation.viewpager.LessonCreateVPAdapter
import ru.fevgenson.timetable.libraries.core.presentation.dialogs.ListDialogFragment
import ru.fevgenson.timetable.libraries.core.presentation.dialogs.NoticeDialogFragment
import ru.fevgenson.timetable.libraries.core.presentation.fragment.BaseFragment
import ru.fevgenson.timetable.libraries.core.presentation.utils.keyboardutils.closeKeyboard
import ru.fevgenson.timetable.libraries.core.utils.dateutils.MyTimeUtils
import ru.fevgenson.timetable.libraries.flowbinding.pageBind
import ru.fevgenson.timetable.libraries.flowbinding.textResBind


@ExperimentalCoroutinesApi
class LessonCreateFragment :
    BaseFragment<FragmentLessonCreateBinding>(),
    LessonCreateViewModel.EventListener,
    NoticeDialogFragment.NoticeDialogListener<Int>,
    ListDialogFragment.ListDialogListener {

    private val lessonCreateViewModel: LessonCreateViewModel by viewModel {
        with(requireArguments()) {
            with(NavigationConstants.LessonCreate) {
                parametersOf(
                    getInt(WEEK_TYPE, NOT_INIT),
                    getInt(DAY, NOT_INIT),
                    getLong(LESSON_ID, NOT_INIT.toLong()),
                    getInt(OPEN_TYPE)
                )
            }
        }
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLessonCreateBinding = FragmentLessonCreateBinding.inflate(inflater, container, false)

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initViewPager()
        overrideSystemBackButton()
        initEventListener()
    }

    private fun initObservers() {
        with(binding) {
            backButton.setOnClickListener {
                lessonCreateViewModel.onBackButtonClick()
            }
            toolbarTitle.textResBind(lessonCreateViewModel.toolbarTitle, coroutineScope)
            viewPagerCreateLesson.pageBind(lessonCreateViewModel.currentPage, coroutineScope)
            menuImageView.initMenu(R.menu.lesson_create_menu) {
                lessonCreateViewModel.onClearItemClick()
            }
        }
    }

    private fun initViewPager() {
        val adapter = LessonCreateVPAdapter(lessonCreateViewModel, coroutineScope)
        binding.viewPagerCreateLesson.offscreenPageLimit = adapter.itemCount
        binding.viewPagerCreateLesson.adapter = adapter
        binding.viewPagerCreateLesson.isUserInputEnabled = false
    }

    private fun overrideSystemBackButton() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            lessonCreateViewModel.onBackButtonClick()
        }
    }

    private fun initEventListener() {
        lessonCreateViewModel.eventsDispatcher.observe(viewLifecycleOwner, this)
    }

    override fun closeKeyboard() {
        closeKeyboard(binding.root)
    }

    override fun setTimeAndInvokeTimePicker(timeBorder: MyTimeUtils.TimeBorders) {
        val minutesInHour = MyTimeUtils.MINUTES_IN_HOUR
        val defaultTimeInMinutes = MyTimeUtils.DEFAULT_TIME

        if (timeBorder == MyTimeUtils.TimeBorders.START) {
            invokeTimePickerDialog(
                lessonCreateViewModel.timeStartMinutes.value?.div(minutesInHour)
                    ?: defaultTimeInMinutes,
                lessonCreateViewModel.timeStartMinutes.value?.rem(minutesInHour)
                    ?: defaultTimeInMinutes,
                timeBorder
            )
        } else {
            invokeTimePickerDialog(
                lessonCreateViewModel.timeEndMinutes.value?.div(minutesInHour)
                    ?: defaultTimeInMinutes,
                lessonCreateViewModel.timeEndMinutes.value?.rem(minutesInHour)
                    ?: defaultTimeInMinutes,
                timeBorder
            )
        }
    }

    private fun invokeTimePickerDialog(
        hoursOnStart: Int,
        minOnStart: Int,
        timeBorder: MyTimeUtils.TimeBorders
    ) {
        val listener = TimePickerDialog.OnTimeSetListener { _: TimePicker, ours: Int, min: Int ->
            lessonCreateViewModel.onDoneTimePickerSetTime(ours, min, timeBorder)
        }

        TimePickerDialog(requireContext(), listener, hoursOnStart, minOnStart, true).show()
    }

    override fun showPopupMessage(message: Int) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun navigateToTimetable() {
        findNavController().popBackStack()
    }

    override fun showDialog(title: Int, description: Int, action: Int) {
        val dialog = NoticeDialogFragment.newInstance(
            title = title,
            description = description,
            confirmButtonText = R.string.lesson_create_button_confirm,
            cancelButtonText = R.string.lesson_create_button_cancel,
            action = action
        )
        dialog.setTargetFragment(this, 0)
        dialog.show(parentFragmentManager, "notification")
    }

    override fun showDialog(title: Int, content: List<String>) {
        val dialog = ListDialogFragment.newInstance(
            title = title,
            cancelButtonText = R.string.lesson_create_button_cancel,
            list = content
        )
        dialog.setTargetFragment(this, 0)
        dialog.show(parentFragmentManager, "notification")
    }

    override fun onDialogPositiveClick(action: Int) {
        lessonCreateViewModel.onDialogResult(action)
    }

    override fun onItemSelected(item: String) {
        lessonCreateViewModel.onDialogResult(item)
    }
}