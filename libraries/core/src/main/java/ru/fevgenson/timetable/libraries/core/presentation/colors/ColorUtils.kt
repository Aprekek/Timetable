package ru.fevgenson.timetable.libraries.core.presentation.colors

import android.content.Context
import android.util.TypedValue

object ColorUtils {

    fun getColorFromAttribute(attr: Int, context: Context): Int = TypedValue().apply {
        context.theme.resolveAttribute(attr, this, true)
    }.data
}