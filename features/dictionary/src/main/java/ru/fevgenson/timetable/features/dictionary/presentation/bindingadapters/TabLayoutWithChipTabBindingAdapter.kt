package ru.fevgenson.timetable.features.dictionary.presentation.bindingadapters

import android.content.res.ColorStateList
import androidx.databinding.BindingAdapter
import com.google.android.material.chip.Chip
import com.google.android.material.tabs.TabLayout

@BindingAdapter(
    "backgroundColorSelected",
    "backgroundColorUnselected",
    "textColorSelected",
    "textColorUnselected"
)
fun TabLayout.setColorStateList(
    backgroundColorSelected: ColorStateList,
    backgroundColorUnselected: ColorStateList,
    textColorSelected: ColorStateList,
    textColorUnselected: ColorStateList
) {
    addOnTabSelectedListener(
        object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.setColors(backgroundColorSelected, textColorSelected)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.setColors(backgroundColorUnselected, textColorUnselected)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                tab?.setColors(backgroundColorSelected, textColorSelected)
            }
        }
    )

    selectTab(getTabAt(0))
}

fun TabLayout.Tab.setColors(
    backgroundColor: ColorStateList,
    textColor: ColorStateList
) {
    (customView as? Chip)?.apply {
        setTextColor(textColor)
        chipBackgroundColor = backgroundColor
    }
}