package ru.fevgenson.timetable.features.lessoncreate.presentation.bindingadapters

import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2

@BindingAdapter("page")
fun ViewPager2.setPage(page: Int) {
    setCurrentItem(page, true)
}