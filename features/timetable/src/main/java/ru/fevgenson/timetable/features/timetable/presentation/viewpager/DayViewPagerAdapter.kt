package ru.fevgenson.timetable.features.timetable.presentation.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.fevgenson.timetable.libraries.core.utils.dateutils.DateUtils

class DayViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = DateUtils.WEEK_DAYS

    override fun createFragment(position: Int): Fragment = PageDayFragment()

    override fun getItemId(position: Int): Long = position.toLong()
}