package ru.fevgenson.timetable.features.timetable.presentation.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class DayViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private companion object {
        const val DAYS_COUNT = 7
    }

    override fun getItemCount(): Int = DAYS_COUNT

    override fun createFragment(position: Int): Fragment = PageDayFragment()

    override fun getItemId(position: Int): Long = position.toLong()
}