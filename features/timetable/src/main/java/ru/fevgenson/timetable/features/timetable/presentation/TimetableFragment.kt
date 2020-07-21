package ru.fevgenson.timetable.features.timetable.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.fevgenson.timetable.features.timetable.R
import ru.fevgenson.timetable.features.timetable.databinding.FragmentTimetableBinding
import ru.fevgenson.timetable.features.timetable.databinding.TabTimetableBinding
import ru.fevgenson.timetable.features.timetable.presentation.viewpager.DayViewPagerAdapter
import ru.fevgenson.timetable.libraries.core.dateutils.DateUtils

class TimetableFragment : Fragment() {

    private lateinit var binding: FragmentTimetableBinding
    private val viewModel: TimetableViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initBinding(inflater, container)
        initViewPagerAdapter()
        initWeekTabs()
        initDayTab()
        return binding.root
    }

    private fun initBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_timetable, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }

    private fun initViewPagerAdapter() {
        binding.dayViewPager.adapter = DayViewPagerAdapter(this)
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
}