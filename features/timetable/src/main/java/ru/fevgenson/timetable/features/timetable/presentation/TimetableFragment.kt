package ru.fevgenson.timetable.features.timetable.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import ru.fevgenson.timetable.features.timetable.R
import ru.fevgenson.timetable.features.timetable.databinding.FragmentTimetableBinding
import ru.fevgenson.timetable.features.timetable.databinding.TabTimetableBinding
import ru.fevgenson.timetable.features.timetable.presentation.viewpager.DayViewPagerAdapter
import ru.fevgenson.timetable.libraries.core.utils.dateutils.DateUtils

class TimetableFragment : Fragment(), TimetableViewModel.EventListener {

    private lateinit var binding: FragmentTimetableBinding
    private val viewModel: TimetableViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initBinding(inflater, container)
        initViewPagerAdapter()
        initWeekTabs()
        initDayTab()
        initEventListener()
        return binding.root
    }

    private fun initBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_timetable, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }

    private fun initViewPagerAdapter() {
        val adapter = DayViewPagerAdapter(this)
        binding.dayViewPager.adapter = adapter
        binding.dayViewPager.offscreenPageLimit = adapter.itemCount
    }

    private fun initWeekTabs() {
        val tabTitles = resources.getStringArray(R.array.timetable_week_tabs)
        val currentWeek = DateUtils.getCurrentWeek()
        with(binding.weekTabLayout) {
            for (position in 0 until DateUtils.WEEK_TYPES) {
                addTab(
                    newTab().createTab(
                        parent = this,
                        text = tabTitles[position],
                        showIcon = currentWeek == position
                    )
                )
            }
        }
    }

    private fun initDayTab() {
        val tabsTitles = resources.getStringArray(R.array.timetable_day_tabs)
        val tabsDates = DateUtils.getWeekDates(binding.weekTabLayout.selectedTabPosition)
        val currentDay = DateUtils.getCurrentDay()
        TabLayoutMediator(
            binding.dayTabLayout,
            binding.dayViewPager
        ) { tab: TabLayout.Tab, position: Int ->
            tab.createTab(
                parent = binding.dayTabLayout,
                text = tabsTitles[position],
                date = tabsDates[position],
                showIcon = position == currentDay
            )
        }.attach()
    }

    private fun initEventListener() {
        viewModel.eventsDispatcher.observe(viewLifecycleOwner, this)
    }

    private fun TabLayout.Tab.createTab(
        parent: ViewGroup,
        text: String,
        date: String? = null,
        showIcon: Boolean
    ): TabLayout.Tab = apply {
        val tabBinding = DataBindingUtil.inflate<TabTimetableBinding>(
            LayoutInflater.from(context),
            R.layout.tab_timetable,
            parent,
            false
        )
        tabBinding.showIcon = showIcon
        tabBinding.text = text
        tabBinding.date = date
        customView = tabBinding.root
    }

    override fun navigateToCreate() {
        Navigation.findNavController(
            requireActivity(),
            R.id.global_host
        ).navigate(R.id.navigation_from_main_to_lesson_create)
    }
}