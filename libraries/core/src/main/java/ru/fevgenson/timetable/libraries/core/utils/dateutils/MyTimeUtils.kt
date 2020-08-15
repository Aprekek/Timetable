package ru.fevgenson.timetable.libraries.core.utils.dateutils

object MyTimeUtils {
    enum class TimeBorders {
        START, END
    }

    const val DEFAULT_TIME = 0
    const val MINUTES_IN_HOUR = 60
    const val TEN_MINUTES = 10

    fun convertTimeInMinutesToString(timeMinutes: Int): String {
        val ours = timeMinutes.div(MINUTES_IN_HOUR)
        val minutes = timeMinutes.rem(MINUTES_IN_HOUR)

        val oursStr = if (ours < TEN_MINUTES) "0$ours" else ours.toString()
        val minutesStr = if (minutes < TEN_MINUTES) "0$minutes" else minutes.toString()

        return "$oursStr : $minutesStr"
    }

    fun convertSeparatedTimeToString(hours: Int, minutes: Int): String {
        val oursStr = if (hours < TEN_MINUTES) "0$hours" else hours.toString()
        val minutesStr = if (minutes < TEN_MINUTES) "0$minutes" else minutes.toString()

        return "$oursStr : $minutesStr"
    }
}