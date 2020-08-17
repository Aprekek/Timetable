package ru.fevgenson.timetable.features.timetable.presentation.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import org.koin.androidx.viewmodel.ext.android.getSharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import ru.fevgenson.timetable.features.timetable.R
import ru.fevgenson.timetable.features.timetable.databinding.FragmentTimetablePageDayBinding
import ru.fevgenson.timetable.features.timetable.presentation.TimetableViewModel
import ru.fevgenson.timetable.features.timetable.presentation.recyclerview.LessonListAdapter
import ru.fevgenson.timetable.features.timetable.presentation.recyclerview.LessonRecyclerViewItemDecoration

class PageDayFragment : Fragment() {

    companion object {

        private const val DAY = "DAY"

        fun newInstance(day: Int): PageDayFragment = PageDayFragment().apply {
            arguments = Bundle().apply {
                putInt(DAY, day)
            }
        }
    }

    private lateinit var binding: FragmentTimetablePageDayBinding
    private val viewModel: PageDayViewModel by viewModel {
        parametersOf(
            arguments?.getInt(DAY),
            getSharedViewModel<TimetableViewModel>()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initBinding(inflater, container)
        initRecyclerView()
        return binding.root
    }

    private fun initBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_timetable_page_day,
            container,
            false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun initRecyclerView() {
        val dp20 = resources.getDimensionPixelSize(R.dimen.space_20)
        binding.firstWeekRecyclerView.addItemDecoration(
            LessonRecyclerViewItemDecoration(
                verticalSpacePx = dp20,
                horizontalSpacePx = dp20
            )
        )
        binding.secondWeekRecyclerView.addItemDecoration(
            LessonRecyclerViewItemDecoration(
                verticalSpacePx = dp20,
                horizontalSpacePx = dp20
            )
        )
        val firstWeekAdapter = LessonListAdapter()
        val secondWeekAdapter = LessonListAdapter()
        binding.firstWeekRecyclerView.adapter = firstWeekAdapter
        binding.secondWeekRecyclerView.adapter = secondWeekAdapter
        viewModel.firstWeekLessons.observe(viewLifecycleOwner) {
            firstWeekAdapter.submitList(it)
        }
        viewModel.secondWeekLessons.observe(viewLifecycleOwner) {
            secondWeekAdapter.submitList(it)
        }
    }
}