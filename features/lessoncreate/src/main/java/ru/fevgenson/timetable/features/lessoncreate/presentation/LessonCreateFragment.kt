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
import androidx.viewpager2.widget.ViewPager2
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.fevgenson.timetable.features.lessoncreate.R
import ru.fevgenson.timetable.features.lessoncreate.databinding.FragmentLessonCreateBinding
import ru.fevgenson.timetable.features.lessoncreate.presentation.viewpager.LessonCreateVPAdapter
import ru.fevgenson.timetable.libraries.core.presentation.utils.keyboardutils.closeKeyboard
import ru.fevgenson.timetable.libraries.core.utils.timeutils.MyTimeUtils

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
        val adapter = LessonCreateVPAdapter(lessonCreateViewModel, viewLifecycleOwner)
        binding.viewPagerCreateLesson.offscreenPageLimit = adapter.itemCount
        binding.viewPagerCreateLesson.adapter = adapter
        binding.viewPagerCreateLesson.isUserInputEnabled = false

        binding.viewPagerCreateLesson.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.toolbarTitle.text = when (position) {
                    LessonCreateViewModel.MAIN_PAGE -> getString(R.string.lesson_create_title_main_info)
                    LessonCreateViewModel.LOCATION_AND_TYPE_PAGE -> getString(R.string.lesson_create_title_location_and_type)
                    LessonCreateViewModel.TEACHER_PAGE -> getString(R.string.lesson_create_title_teacher)
                    else -> throw IllegalStateException("Page $position not found")
                }
            }
        })
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

    override fun setTimeAndInvokeTimePicker(timeBorder: MyTimeUtils.TimeBorders) {
        if (timeBorder == MyTimeUtils.TimeBorders.START) {
            invokeTimePickerDialog(
                lessonCreateViewModel.timeStartMinutes.value?.div(60) ?: 0,
                lessonCreateViewModel.timeStartMinutes.value?.rem(60) ?: 0,
                timeBorder
            )
        } else {
            invokeTimePickerDialog(
                lessonCreateViewModel.timeEndMinutes.value?.div(60) ?: 0,
                lessonCreateViewModel.timeEndMinutes.value?.rem(60) ?: 0,
                timeBorder
            )
        }
    }

    private fun invokeTimePickerDialog(
        oursOnStart: Int,
        minOnStart: Int,
        timeBorder: MyTimeUtils.TimeBorders
    ) {
        val listener = TimePickerDialog.OnTimeSetListener { _: TimePicker, ours: Int, min: Int ->
            binding.lessonCreateViewModel?.onDoneTimePickerSetTime(ours, min, timeBorder)
        }

        TimePickerDialog(requireContext(), listener, oursOnStart, minOnStart, true).show()
    }
}