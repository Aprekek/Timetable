package ru.fevgenson.timetable.libraries.core.utils.dateutils

import android.content.Context
import ru.fevgenson.timetable.libraries.core.R

@Deprecated("Используйте утилиты из shared модуля")
object ShowedTimeUtils {

    fun getShowedMinutesText(
        lessonDiapason: String,
        context: Context
    ): String? {
        val minutesBeforeStart = getMinutesBeforeStart(lessonDiapason)
        val minutesBeforeEnd = getMinutesBeforeEnd(lessonDiapason)
        return when {
            minutesBeforeStart != null -> context.getString(
                R.string.core_mask_before_start,
                MyTimeUtils.convertTimeInMinutesToString(minutesBeforeStart)
            )
            minutesBeforeEnd != null -> context.getString(
                R.string.core_mask_before_end,
                MyTimeUtils.convertTimeInMinutesToString(minutesBeforeEnd)
            )
            else -> null
        }
    }

    fun currentTimeInThisDiapason(diapason: String): Boolean =
        MyTimeUtils.getCurrentTime().let { currentTime ->
            val startTime =
                MyTimeUtils.convertDbTimeToMinutes(diapason, MyTimeUtils.TimeBorders.START)
            val endTime = MyTimeUtils.convertDbTimeToMinutes(diapason, MyTimeUtils.TimeBorders.END)
            currentTime in startTime..endTime
        }

    private fun getMinutesBeforeStart(diapason: String): Int? =
        MyTimeUtils.getCurrentTime().let { currentTime ->
            val startTime =
                MyTimeUtils.convertDbTimeToMinutes(diapason, MyTimeUtils.TimeBorders.START)
            if (startTime < currentTime) {
                null
            } else {
                startTime - currentTime
            }
        }

    private fun getMinutesBeforeEnd(diapason: String): Int? =
        MyTimeUtils.getCurrentTime().let { currentTime ->
            val startTime =
                MyTimeUtils.convertDbTimeToMinutes(diapason, MyTimeUtils.TimeBorders.START)
            if (startTime > currentTime) {
                return@let null
            }
            val endTime = MyTimeUtils.convertDbTimeToMinutes(diapason, MyTimeUtils.TimeBorders.END)
            if (endTime < currentTime) {
                return@let null
            }
            endTime - currentTime
        }
}