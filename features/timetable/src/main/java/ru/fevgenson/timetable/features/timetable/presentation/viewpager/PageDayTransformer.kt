package ru.fevgenson.timetable.features.timetable.presentation.viewpager

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs
import kotlin.math.max

class PageDayTransformer : ViewPager2.PageTransformer {

    private companion object {
        const val MIN_SCALE = 0.85f
        const val MIN_ALPHA = 0.5f
        const val LEFT_OFFSCREEN = -1
        const val RIGHT_OFFSCREEN = 1
    }

    override fun transformPage(view: View, position: Float) {
        view.apply {
            val pageWidth = width
            val pageHeight = height
            when {
                position < LEFT_OFFSCREEN -> {
                    alpha = 0f
                }
                position <= RIGHT_OFFSCREEN -> {
                    val scaleFactor = max(MIN_SCALE, 1 - abs(position))
                    val verticalMargin = pageHeight * (1 - scaleFactor) / 2
                    val horizontalMargin = pageWidth * (1 - scaleFactor) / 2
                    translationX = if (position < 0) {
                        horizontalMargin - verticalMargin / 2
                    } else {
                        horizontalMargin + verticalMargin / 2
                    }
                    scaleX = scaleFactor
                    scaleY = scaleFactor
                    alpha =
                        MIN_ALPHA + ((scaleFactor - MIN_SCALE) / (1 - MIN_SCALE)) * (1 - MIN_ALPHA)
                }
                position > RIGHT_OFFSCREEN -> {
                    alpha = 0f
                }
            }
        }
    }
}