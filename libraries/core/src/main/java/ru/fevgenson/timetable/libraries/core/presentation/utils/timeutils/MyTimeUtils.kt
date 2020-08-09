package ru.fevgenson.timetable.libraries.core.presentation.utils.timeutils

object MyTimeUtils {
    enum class TimeBorders {
        START, END
    }

    fun convertTimeInMinutesToString(timeMinutes: Int): String {
        val ours = timeMinutes.div(60)
        val minutes = timeMinutes.rem(60)

        val oursStr = if (ours < 10) "0$ours" else ours.toString()
        val minutesStr = if (minutes < 10) "0$minutes" else minutes.toString()

        return "$oursStr : $minutesStr"
    }

    fun convertSeparatedTimeToString(ours: Int, minutes: Int): String {
        val oursStr = if (ours < 10) "0$ours" else ours.toString()
        val minutesStr = if (minutes < 10) "0$minutes" else minutes.toString()

        return "$oursStr : $minutesStr"
    }
}