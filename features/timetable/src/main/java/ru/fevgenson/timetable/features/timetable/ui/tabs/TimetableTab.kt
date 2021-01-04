package ru.fevgenson.timetable.features.timetable.ui.tabs

import android.view.LayoutInflater
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import ru.fevgenson.timetable.features.timetable.databinding.TabTimetableBinding

fun TabLayout.createTab(): TabLayout.Tab =
    newTab().apply {
        val tabBinding = TabTimetableBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        customView = tabBinding.root
    }

fun TabLayout.Tab.createTabFrom(parent: ViewGroup) {
    val tabBinding = TabTimetableBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
    )
    customView = tabBinding.root
}

val TabLayout.Tab.binding: TabTimetableBinding
    get() = customView?.let(TabTimetableBinding::bind)
        ?: throw NullPointerException("binding can't be null")