package ru.fevgenson.timetable.libraries.core.utils.dateutils

object MyTimeUtils {
    enum class TimeBorders {
        START, END
    }

    const val DEFAULT_TIME = 0
    const val MINUTES_IN_HOUR = 60
    const val TEN_MINUTES = 10

    private const val DB_FIRST_TIME_START = 0
    private const val DB_FIRST_TIME_END = 5
    private const val DB_SECOND_TIME_START = 6
    private const val DB_HOURS_START = 0
    private const val DB_HOURS_END = 2
    private const val DB_MINUTES_START = 3

    fun convertTimeInMinutesToString(timeMinutes: Int): String {
        val ours = timeMinutes.div(MINUTES_IN_HOUR)
        val minutes = timeMinutes.rem(MINUTES_IN_HOUR)

        val oursStr = if (ours < TEN_MINUTES) "0$ours" else ours.toString()
        val minutesStr = if (minutes < TEN_MINUTES) "0$minutes" else minutes.toString()

        return "$oursStr:$minutesStr"
    }

    fun convertSeparatedTimeToString(hours: Int, minutes: Int): String {
        val oursStr = if (hours < TEN_MINUTES) "0$hours" else hours.toString()
        val minutesStr = if (minutes < TEN_MINUTES) "0$minutes" else minutes.toString()

        return "$oursStr:$minutesStr"
    }

    fun convertEditTimesToDbTimes(start: String, end: String) = "$start-$end"

    fun convertDbTimeToMinutes(dbTime: String, timeType: TimeBorders): Int {
        val time = when (timeType) {
            TimeBorders.START -> dbTime.substring(DB_FIRST_TIME_START, DB_FIRST_TIME_END)
            TimeBorders.END -> dbTime.substring(DB_SECOND_TIME_START)
        }
        val hours = time.substring(DB_HOURS_START, DB_HOURS_END)
        val minutes = time.substring(DB_MINUTES_START)
        return hours.toInt() * MINUTES_IN_HOUR + minutes.toInt()
    }
}