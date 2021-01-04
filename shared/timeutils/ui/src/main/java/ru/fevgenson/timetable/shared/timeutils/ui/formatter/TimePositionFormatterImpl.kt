package ru.fevgenson.timetable.shared.timeutils.ui.formatter

import android.content.Context
import ru.fevgenson.timetable.shared.timeutils.domain.formatter.TimeFormatter
import ru.fevgenson.timetable.shared.timeutils.domain.usecase.GetCurrentTimeUseCase
import ru.fevgenson.timetable.shared.timeutils.ui.R

internal class TimePositionFormatterImpl(
    private val getCurrentTimeUseCase: GetCurrentTimeUseCase,
    private val timeFormatter: TimeFormatter,
    private val context: Context
) : TimePositionFormatter {

    override fun format(diapason: String): String? {
        val currentTime = getCurrentTimeUseCase()
        val startTime = timeFormatter.getMinutes(diapason, TimeFormatter.TimeBorders.START)
        val endTime = timeFormatter.getMinutes(diapason, TimeFormatter.TimeBorders.END)
        return when {
            currentTime < startTime -> {
                val beforeStart = timeFormatter.format(startTime - currentTime)
                context.getString(R.string.time_utils_mask_before_start, beforeStart)
            }

            currentTime in startTime..endTime -> {
                val beforeEnd = timeFormatter.format(endTime - currentTime)
                context.getString(R.string.time_utils_mask_before_end, beforeEnd)
            }

            else -> null
        }
    }
}