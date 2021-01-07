package ru.fevgenson.timetable.shared.timeutils.domain.usecase

import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.fevgenson.timetable.shared.timeutils.domain.converter.CalendarConverter
import java.util.*

class GetCurrentDayUseCaseTest {

    private companion object {

        const val CALENDAR_DAY = 5
        const val CONVERTED_DAY = 3
    }

    private val calendar: Calendar = mockk()
    private val converter: CalendarConverter = mockk()

    private val getCurrentDayUseCase = GetCurrentDayUseCase(converter)

    @Before
    fun before() {
        mockkStatic(Calendar::class)
        every { Calendar.getInstance() }.returns(calendar)
    }

    @Test
    fun `get current day EXPECT converted day`() {
        every { calendar.get(Calendar.DAY_OF_WEEK) }.returns(CALENDAR_DAY)
        every { converter.convertWeekDay(CALENDAR_DAY) }.returns(CONVERTED_DAY)

        val currentDay = getCurrentDayUseCase()

        assertEquals(currentDay, CONVERTED_DAY)
    }

    @Test
    fun `get current day EXPECT invoke converter format`() {
        every { calendar.get(Calendar.DAY_OF_WEEK) }.returns(CALENDAR_DAY)
        every { converter.convertWeekDay(CALENDAR_DAY) }.returns(CONVERTED_DAY)

        getCurrentDayUseCase()

        verify { converter.convertWeekDay(CALENDAR_DAY) }
    }
}