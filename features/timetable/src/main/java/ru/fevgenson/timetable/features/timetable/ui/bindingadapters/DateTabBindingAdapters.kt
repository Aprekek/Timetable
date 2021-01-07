package ru.fevgenson.timetable.features.timetable.ui.bindingadapters

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import androidx.core.view.isVisible
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.fevgenson.timetable.features.timetable.databinding.TabTimetableBinding
import ru.fevgenson.timetable.features.timetable.ui.tabs.binding

fun TabLayout.setTabsColors(selectedColor: Int, normalColor: Int) {
    addOnTabSelectedListener(
        object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                tab?.binding?.setColor(selectedColor)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.binding?.setColor(normalColor)
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.binding?.setColor(selectedColor)
            }
        }
    )
}

fun TabLayout.setCurrentWeekIcon(
    currentWeekFlow: Flow<Int>,
    coroutineScope: CoroutineScope
) {
    currentWeekFlow.onEach {
        for (position in 0 until tabCount) {
            val tabBinding = getTabAt(position)?.binding
                ?: throw IllegalAccessException("Can't find day tab at $position")
            tabBinding.icon.isVisible = position == it
        }
    }.launchIn(coroutineScope)
}

fun TabLayout.setCurrentDayIcon(
    isCurrentWeekFlow: Flow<Boolean>,
    currentDayFlow: Flow<Int>,
    coroutineScope: CoroutineScope
) {
    isCurrentWeekFlow.combine(currentDayFlow) { isCurrentWeek: Boolean, currentDay: Int ->
        for (position in 0 until tabCount) {
            val tabBinding = getTabAt(position)?.binding
                ?: throw IllegalAccessException("Can't find day tab at $position")
            tabBinding.icon.isVisible = position == currentDay && isCurrentWeek
        }
    }.launchIn(coroutineScope)
}

fun TabLayout.setDates(
    datesFlow: Flow<List<String>>,
    coroutineScope: CoroutineScope
) {
    datesFlow.onEach {
        for (position in 0 until tabCount) {
            val tabBinding = getTabAt(position)?.binding
                ?: throw IllegalAccessException("Can't find day tab at $position")
            tabBinding.dateTextView.text = it[position]
        }
    }.launchIn(coroutineScope)
}

private fun TabTimetableBinding.setColor(color: Int) {
    nameTextView.setTextColor(color)
    dateTextView.setTextColor(color)
    icon.drawable.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
}