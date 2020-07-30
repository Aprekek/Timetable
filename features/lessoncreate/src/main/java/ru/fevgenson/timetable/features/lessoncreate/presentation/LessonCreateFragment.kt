package ru.fevgenson.timetable.features.lessoncreate.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.fevgenson.timetable.features.lessoncreate.R
import ru.fevgenson.timetable.features.lessoncreate.databinding.FragmentLessonCreateBinding
import ru.fevgenson.timetable.features.lessoncreate.presentation.viewpager.LessonCreateVPAdapter

class LessonCreateFragment : Fragment() {

    private lateinit var binding: FragmentLessonCreateBinding
    private val lessonCreateViewModel: LessonCreateViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initBinding(inflater, container)
        initViewPager()
//        initToolBar()

//        setHasOptionsMenu(true)

        return binding.root
    }

    private fun initBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_lesson_create, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.lessonCreateViewModel = lessonCreateViewModel
    }

    private fun initViewPager() {
        binding.viewPagerCreateLesson.adapter = LessonCreateVPAdapter(lessonCreateViewModel)
        binding.viewPagerCreateLesson.isUserInputEnabled = false
    }

//    private fun initToolBar() {
////        binding.toolbarCreateLesson.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
////        binding.toolbarCreateLesson.setNavigationOnClickListener {
////            Toast.makeText(
////                requireContext(),
////                "tap",
////                Toast.LENGTH_SHORT
////            ).show()
////        }
////        binding.toolbarCreateLesson.inflateMenu(R.menu.lesson_create_menu)
//
//    }

}