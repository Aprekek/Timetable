package ru.fevgenson.timetable.features.timetable.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import ru.fevgenson.timetable.features.timetable.R
import ru.fevgenson.timetable.features.timetable.databinding.FragmentTimetableBinding
import ru.fevgenson.timetable.features.timetable.databinding.TabTimetableBinding
import ru.fevgenson.timetable.features.timetable.presentation.viewpager.DayViewPagerAdapter
import ru.fevgenson.timetable.libraries.core.dateutils.DateUtils

class TimetableFragment : Fragment() {

    private companion object {
        const val WEEK_COUNT = 2
    }

    private lateinit var binding: FragmentTimetableBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_timetable, container, false)
        initWeekTabLayout()
        initViewPagerAdapter()
        return binding.root
    }

    private fun initViewPagerAdapter() {
        binding.dayViewPager.adapter = DayViewPagerAdapter(this)
        val tabsTitles = resources.getStringArray(R.array.timetable_day_tabs)
        val tabsDates =
            DateUtils.getWeekDates(binding.includeTimetableToolbar.weekTabLayout.selectedTabPosition)
        TabLayoutMediator(
            binding.includeTimetableToolbar.dayTabLayout,
            binding.dayViewPager
        ) { tab: TabLayout.Tab, position: Int ->
            val tabBinding = DataBindingUtil.inflate<TabTimetableBinding>(
                LayoutInflater.from(requireContext()),
                R.layout.tab_timetable,
                binding.includeTimetableToolbar.dayTabLayout,
                false
            )
            tabBinding.text = tabsTitles[position]
            tabBinding.date = tabsDates[position]
            tab.customView = tabBinding.root
        }.attach()
    }

    private fun initWeekTabLayout() {
        val tabTitles = resources.getStringArray(R.array.timetable_week_tabs)
        for (position in 0 until WEEK_COUNT) {
            with(binding.includeTimetableToolbar.weekTabLayout) {
                addTab(
                    newTab().apply {
                        val tabBinding = DataBindingUtil.inflate<TabTimetableBinding>(
                            LayoutInflater.from(requireContext()),
                            R.layout.tab_timetable,
                            binding.includeTimetableToolbar.weekTabLayout,
                            false
                        )
                        tabBinding.text = tabTitles[position]
                        customView = tabBinding.root
                    }
                )
            }
        }
        binding.includeTimetableToolbar.weekTabLayout.addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(tab: TabLayout.Tab?) {}

                override fun onTabUnselected(tab: TabLayout.Tab?) {}

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    updateDayTabs()
                }
            }
        )
    }

    private fun updateDayTabs() {
        val tabDates =
            DateUtils.getWeekDates(binding.includeTimetableToolbar.weekTabLayout.selectedTabPosition)
        with(binding.includeTimetableToolbar.dayTabLayout) {
            for (position in 0 until tabCount) {
                val tabView = getTabAt(position)?.customView
                    ?: throw IllegalAccessException("Can't find day tab at $position")
                val tabBinding = DataBindingUtil.getBinding<TabTimetableBinding>(tabView)
                tabBinding?.date = tabDates[position]
                tabBinding?.executePendingBindings()
            }
        }
    }
}