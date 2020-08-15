package ru.fevgenson.timetable.features.timetable.presentation.bindingadapters

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayout
import ru.fevgenson.timetable.features.timetable.databinding.TabTimetableBinding
import ru.fevgenson.timetable.libraries.core.utils.dateutils.DateUtils

@BindingAdapter("selectedColor", "normalColor")
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

@BindingAdapter("selectedWeekTab")
fun TabLayout.updateDayTabs(selectedWeekType: Int) {
    val tabDates = DateUtils.getWeekDates(selectedWeekType)
    val isCurrentWeek = DateUtils.getCurrentWeek() == selectedWeekType
    val currentDay = DateUtils.getCurrentDay()
    for (position in 0 until tabCount) {
        val tabView = getTabAt(position)?.customView
            ?: throw IllegalAccessException("Can't find day tab at $position")
        val tabBinding = DataBindingUtil.getBinding<TabTimetableBinding>(tabView)
        tabBinding?.showIcon = isCurrentWeek && currentDay == position
        tabBinding?.date = tabDates[position]
        tabBinding?.executePendingBindings()
    }
}

private fun TabLayout.Tab.setColor(color: Int) {
    customView?.let { tabView ->
        val tabBinding = DataBindingUtil.getBinding<TabTimetableBinding>(tabView)
        tabBinding?.setColor(color)
    }
}

private fun TabTimetableBinding.setColor(color: Int) {
    nameTextView.setTextColor(color)
    dateTextView.setTextColor(color)
    icon.drawable.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
}