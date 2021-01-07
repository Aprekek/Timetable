package ru.fevgenson.timetable.shared.timeutils.domain.formatter

import java.util.concurrent.TimeUnit

internal class TimeFormatterImpl : TimeFormatter {

    private companion object {

        const val SINGLE_TIME_FORMAT = "%02d:%02d"
        const val HYPHEN = "-"
        const val COLON = ":"
    }

    override fun format(minutes: Int): String =
        String.format(SINGLE_TIME_FORMAT, getHours(minutes), getMinutesWithoutHours(minutes))

    override fun format(hours: Int, minutes: Int): String =
        String.format(SINGLE_TIME_FORMAT, hours, minutes)

    override fun format(startTime: String, endTime: String): String = "$startTime-$endTime"

    override fun getMinutes(diapason: String, border: TimeFormatter.TimeBorders): Int {
        val times = diapason.split(HYPHEN)
        val requestedTime = if (border == TimeFormatter.TimeBorders.START) {
            times.first()
        } else {
            times.last()
        }
        val hoursAndMinutes = requestedTime.split(COLON)
        val hours = TimeUnit.HOURS.toMinutes(hoursAndMinutes.first().toLong())
        val minutes = hoursAndMinutes.last().toLong()
        return (hours + minutes).toInt()
    }

    override fun getMinutes(hours: Int, minutes: Int): Int =
        (TimeUnit.HOURS.toMinutes(hours.toLong()) + minutes).toInt()

    override fun getHours(minutes: Int): Int =
        (TimeUnit.MINUTES.toHours(minutes.toLong())).toInt()

    override fun getMinutesWithoutHours(minutes: Int): Int =
        (minutes - TimeUnit.HOURS.toMinutes(getHours(minutes).toLong())).toInt()
}