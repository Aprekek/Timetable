package ru.fevgenson.timetable.features.dictionary.presentation.bindingadapters

import android.content.res.ColorStateList
import androidx.databinding.BindingAdapter
import com.google.android.material.chip.Chip
import com.google.android.material.tabs.TabLayout
import ru.fevgenson.timetable.features.dictionary.R

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
                tab?.setColors(
                    backgroundColorSelected,
                    textColorSelected,
                    resources.getDimension(R.dimen.width_0)
                )
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.setColors(
                    backgroundColorUnselected,
                    textColorUnselected,
                    resources.getDimension(R.dimen.width_1)
                )
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                tab?.setColors(
                    backgroundColorSelected,
                    textColorSelected,
                    resources.getDimension(R.dimen.width_0)
                )
            }
        }
    )

    selectTab(getTabAt(0))
}

fun TabLayout.Tab.setColors(
    backgroundColor: ColorStateList,
    textColor: ColorStateList,
    strokeWidth: Float
) {
    (customView as? Chip)?.apply {
        setTextColor(textColor)
        chipBackgroundColor = backgroundColor
        chipStrokeWidth = strokeWidth
    }
}