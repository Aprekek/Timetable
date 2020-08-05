package ru.fevgenson.timetable.features.lessoncreate.presentation

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.fevgenson.timetable.features.lessoncreate.R
import ru.fevgenson.timetable.features.lessoncreate.databinding.FragmentLessonCreateBinding
import ru.fevgenson.timetable.features.lessoncreate.presentation.viewpager.LessonCreateVPAdapter
import ru.fevgenson.timetable.libraries.core.presentation.utils.keyboardutils.closeKeyboard

class LessonCreateFragment : Fragment(), LessonCreateViewModel.EventListener {

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
        val adapter = LessonCreateVPAdapter(lessonCreateViewModel)
        binding.viewPagerCreateLesson.offscreenPageLimit = adapter.itemCount
        binding.viewPagerCreateLesson.adapter = adapter
        binding.viewPagerCreateLesson.isUserInputEnabled = false
    }

    private fun overrideSystemBackButton() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            lessonCreateViewModel.onTopBackButtonClick()
        }
    }

    private fun initEventListener() {
        lessonCreateViewModel.eventsDispatcher.observe(viewLifecycleOwner, this)
    }

    override fun navigateToTimetable() {
        findNavController().popBackStack()
    }

    override fun closeKeyboard() {
        closeKeyboard(binding)
    }

    override fun setTimeAndInvokeTimePicker() {
        var timeStartMin = binding.lessonCreateViewModel?.timeStartMin
        var timeEndMin = binding.lessonCreateViewModel?.timeEndMin

        if (binding.lessonCreateViewModel?.isBegin == true) {
            if (timeStartMin == null) {
                timeStartMin = if (timeEndMin == null) {
                    0
                } else {
                    if (timeEndMin >= LessonCreateViewModel.LESSON_LENGTH_MIN) timeEndMin - LessonCreateViewModel.LESSON_LENGTH_MIN
                    else LessonCreateViewModel.MINUTES_IN_DAY + timeEndMin - LessonCreateViewModel.LESSON_LENGTH_MIN
                }
            }

            invokeTimePickerDialog(timeStartMin / 60, timeStartMin % 60, true)
        } else {
            if (timeEndMin == null) {
                timeEndMin = if (timeStartMin == null) {
                    0
                } else {
                    (timeStartMin + LessonCreateViewModel.LESSON_LENGTH_MIN) % LessonCreateViewModel.MINUTES_IN_DAY
                }
            }

            invokeTimePickerDialog(timeEndMin / 60, timeEndMin % 60, false)
        }
    }

    private fun invokeTimePickerDialog(oursOnStart: Int, minOnStart: Int, isBegin: Boolean) {
        val listener = TimePickerDialog.OnTimeSetListener { _: TimePicker, ours: Int, min: Int ->
            val oursStr = if (ours < 10) "0$ours" else ours.toString()
            val minStr = if (min < 10) "0$min" else min.toString()

            if (isBegin) {
                binding.lessonCreateViewModel?.timeStartMin = ours * 60 + min
                binding.lessonCreateViewModel?.timeStartString?.set(
                    getString(R.string.lesson_create_button_time_set).format(oursStr, minStr)
                )
            } else {
                binding.lessonCreateViewModel?.timeEndMin = ours * 60 + min
                binding.lessonCreateViewModel?.timeEndString?.set(
                    getString(R.string.lesson_create_button_time_set).format(oursStr, minStr)
                )
            }
        }

        TimePickerDialog(requireContext(), listener, oursOnStart, minOnStart, true).show()
    }
}