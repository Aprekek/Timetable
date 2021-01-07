package ru.fevgenson.timetable.shared.timeutils.domain.usecase

import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.*

class GetCurrentTimeUseCaseTest {

    private companion object {

        const val FULL_MINUTES_21_30 = 1290
        const val HOURS_21_30 = 21
        const val MINUTES_21_30 = 30
    }

    private val calendar: Calendar = mockk()

    private val getCurrentTimeUseCase = GetCurrentTimeUseCase()

    @Before
    fun before() {
        mockkStatic(Calendar::class)
        every { Calendar.getInstance() }.returns(calendar)
    }

    @Test
    fun `get current day EXPECT converted day`() {
        every { calendar.time = any() }.returns(Unit)
        every { calendar.get(Calendar.HOUR_OF_DAY) }.returns(HOURS_21_30)
        every { calendar.get(Calendar.MINUTE) }.returns(MINUTES_21_30)

        val currentTime = getCurrentTimeUseCase()

        assertEquals(currentTime, FULL_MINUTES_21_30)
    }
}