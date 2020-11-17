package ru.fevgenson.timetable.features.timetable.ui.bindingadapters

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import androidx.core.view.isVisible
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.fevgenson.timetable.features.timetable.databinding.TabTimetableBinding
import ru.fevgenson.timetable.libraries.core.utils.dateutils.DateUtils

fun TabLayout.setTabsColors(selectedColor: Int, normalColor: Int) {
    addOnTabSelectedListener(
        object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                tab?.setColor(selectedColor)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.setColor(normalColor)
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.setColor(selectedColor)
            }
        }
    )
}

fun TabLayout.updateDayTabs(
    selectedWeekTypeFlow: Flow<Int>,
    coroutineScope: CoroutineScope
) {
    selectedWeekTypeFlow.onEach {
        val tabDates = DateUtils.getWeekDates(it)
        val isCurrentWeek = DateUtils.getCurrentWeek() == it
        val currentDay = DateUtils.getCurrentDay()
        for (position in 0 until tabCount) {
            val tabView = getTabAt(position)?.customView
                ?: throw IllegalAccessException("Can't find day tab at $position")
            val tabBinding = TabTimetableBinding.bind(tabView)
            tabBinding.icon.isVisible = isCurrentWeek && currentDay == position
            tabBinding.dateTextView.text = tabDates[position]
        }
    }.launchIn(coroutineScope)
}

private fun TabLayout.Tab.setColor(color: Int) {
    customView?.let { tabView ->
        val tabBinding = TabTimetableBinding.bind(tabView)
        tabBinding.setColor(color)
    }
}

private fun TabTimetableBinding.setColor(color: Int) {
    nameTextView.setTextColor(color)
    dateTextView.setTextColor(color)
    icon.drawable.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
}