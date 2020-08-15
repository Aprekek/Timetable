package ru.fevgenson.timetable.features.lessoncreate.presentation

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import ru.fevgenson.timetable.features.lessoncreate.R
import ru.fevgenson.timetable.features.lessoncreate.databinding.FragmentLessonCreateBinding
import ru.fevgenson.timetable.features.lessoncreate.presentation.viewpager.LessonCreateVPAdapter
import ru.fevgenson.timetable.libraries.core.presentation.dialogs.NoticeDialogFragment
import ru.fevgenson.timetable.libraries.core.presentation.utils.keyboardutils.closeKeyboard
import ru.fevgenson.timetable.libraries.core.presentation.utils.timeutils.MyTimeUtils
import ru.fevgenson.timetable.libraries.core.providers.ResourceProvider

class LessonCreateFragment : Fragment(), LessonCreateViewModel.EventListener,
    NoticeDialogFragment.NoticeDialogListener {

    private lateinit var binding: FragmentLessonCreateBinding
    private val lessonCreateViewModel: LessonCreateViewModel by viewModel {
        parametersOf(
            ResourceProvider(requireContext())
        )
    }

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
            lessonCreateViewModel.onTopBackButtonClick()
        }
    }

    private fun initEventListener() {
        lessonCreateViewModel.eventsDispatcher.observe(viewLifecycleOwner, this)
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

    override fun onValidationFailed() {
        Snackbar.make(
            binding.root,
            R.string.lesson_create_fields_filling_request,
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun navigateToTimetable() {
        findNavController().popBackStack()
    }

    override fun showDialog(title: String, description: String, action: Int) {
        val dialog = NoticeDialogFragment()
        dialog.initialize(title, description, _action = action)
        dialog.setTargetFragment(this, 0)
        dialog.show(parentFragmentManager, "notification")
    }

    override fun onDialogPositiveClick(dialog: DialogFragment, action: Int) {
        if (action == LessonCreateViewModel.ACTION_CANCEL)
            lessonCreateViewModel.onCancel()
        else
            lessonCreateViewModel.onDone()
    }
}