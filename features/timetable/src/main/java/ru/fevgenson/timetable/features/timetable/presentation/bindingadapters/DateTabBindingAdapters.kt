package ru.fevgenson.timetable.features.timetable.presentation.bindingadapters

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayout
import ru.fevgenson.timetable.features.timetable.databinding.TabTimetableBinding

@BindingAdapter("selectedColor", "normalColor")
fun TabLayout.setTabsColors(selectedColor: Int, normalColor: Int) {
    addOnTabSelectedListener(
        object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.customView?.let { tabView ->
                    val tabBinding = DataBindingUtil.getBinding<TabTimetableBinding>(tabView)
                    tabBinding?.setColor(normalColor)
                }
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.customView?.let { tabView ->
                    val tabBinding = DataBindingUtil.getBinding<TabTimetableBinding>(tabView)
                    tabBinding?.setColor(selectedColor)
                }
            }
        }
    )
}

private fun TabTimetableBinding.setColor(color: Int) {
    name.setTextColor(color)
    date.setTextColor(color)
    icon.drawable.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)
}