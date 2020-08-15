package ru.fevgenson.timetable.features.lessoncreate.presentation

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.fevgenson.timetable.features.lessoncreate.R
import ru.fevgenson.timetable.features.lessoncreate.databinding.FragmentLessonCreateBinding
import ru.fevgenson.timetable.features.lessoncreate.presentation.viewpager.LessonCreateVPAdapter
import ru.fevgenson.timetable.libraries.core.presentation.dialogs.NoticeDialogFragment
import ru.fevgenson.timetable.libraries.core.presentation.utils.keyboardutils.closeKeyboard
import ru.fevgenson.timetable.libraries.core.utils.dateutils.MyTimeUtils


class LessonCreateFragment : Fragment(), LessonCreateViewModel.EventListener,
    NoticeDialogFragment.NoticeDialogListener {

    private lateinit var binding: FragmentLessonCreateBinding
    private val lessonCreateViewModel: LessonCreateViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initBinding(inflater, container)
        initViewPager()
        overrideSystemBackButton()
        initEventListener()

        return binding.root
    }

    private fun initBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_lesson_create, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.lessonCreateViewModel = lessonCreateViewModel
    }

    private fun initViewPager() {
        val adapter = LessonCreateVPAdapter(lessonCreateViewModel, viewLifecycleOwner)
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
        closeKeyboard(binding)
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
            binding.lessonCreateViewModel?.onDoneTimePickerSetTime(ours, min, timeBorder)
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
        val dialog = NoticeDialogFragment()
        dialog.initialize(
            getString(title),
            getString(description),
            getString(R.string.lesson_create_button_cancel),
            getString(R.string.lesson_create_button_confirm),
            action
        )
        dialog.setTargetFragment(this, 0)
        dialog.show(parentFragmentManager, "notification")
    }

    override fun onDialogPositiveClick(action: Int) {
        if (action == LessonCreateViewModel.ACTION_CANCEL)
            lessonCreateViewModel.onCancel()
        else
            lessonCreateViewModel.onDone()
    }
}