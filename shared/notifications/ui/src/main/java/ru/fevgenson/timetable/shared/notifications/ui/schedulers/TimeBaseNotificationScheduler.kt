package ru.fevgenson.timetable.shared.notifications.ui.schedulers

import androidx.work.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.fevgenson.timetable.shared.lesson.domain.entity.LessonEntity
import ru.fevgenson.timetable.shared.lesson.domain.usecase.GetLessonsByWeekTypeAndDayUseCase
import ru.fevgenson.timetable.shared.notifications.ui.workers.GenerateTimeBaseWorksWorker
import ru.fevgenson.timetable.shared.notifications.ui.workers.TimeBaseNotificationWorker
import ru.fevgenson.timetable.shared.timeutils.domain.scenario.GetMinutesBeforeStartScenario
import ru.fevgenson.timetable.shared.timeutils.domain.scenario.GetNextDayScenario
import ru.fevgenson.timetable.shared.timeutils.domain.scenario.GetNextDayWeekTypeScenario
import ru.fevgenson.timetable.shared.timeutils.domain.usecase.GetCurrentDayUseCase
import ru.fevgenson.timetable.shared.timeutils.domain.usecase.GetCurrentWeekTypeUseCase
import ru.fevgenson.timetable.shared.timeutils.domain.usecase.GetTimeBeforeNextDayUseCase
import java.util.concurrent.TimeUnit

class TimeBaseNotificationScheduler(
    private val getCurrentDayUseCase: GetCurrentDayUseCase,
    private val getCurrentWeekTypeUseCase: GetCurrentWeekTypeUseCase,
    private val getNextDayScenario: GetNextDayScenario,
    private val getNextDayWeekTypeScenario: GetNextDayWeekTypeScenario,
    private val getTimeBeforeNextDayUseCase: GetTimeBeforeNextDayUseCase,
    private val getLessonsByWeekTypeAndDayUseCase: GetLessonsByWeekTypeAndDayUseCase,
    private val getMinutesBeforeStartScenario: GetMinutesBeforeStartScenario,
    private val workManager: WorkManager,
) {

    companion object {

        private const val FLEX_MINUTES = 15L
        private const val ONE_DAY = 1440
        private const val WORK_NAME = "TIME BASE NOTIFICATION WORK"
    }

    fun createTasks(scope: CoroutineScope) {
        scope.launch {
            val currentDayLessons = getLessonsByWeekTypeAndDayUseCase(
                weekType = getCurrentWeekTypeUseCase(),
                day = getCurrentDayUseCase()
            )
            val nextDayLessons = getLessonsByWeekTypeAndDayUseCase(
                weekType = getNextDayWeekTypeScenario(),
                day = getNextDayScenario()
            )
            currentDayLessons.forEach { lesson ->
                handleLesson(lesson, true)
            }
            nextDayLessons.forEach { lesson ->
                handleLesson(lesson, false)
            }
            createUpdateWork().startWork(WORK_NAME)
        }
    }

    fun stopTasks() {
        workManager.cancelAllWork()
    }

    private fun handleLesson(lesson: LessonEntity, isCurrentDayLesson: Boolean) {
        var initialDelayMinutes = getMinutesBeforeStartScenario(lesson.time)
        initialDelayMinutes?.let {
            if (!isCurrentDayLesson) {
                initialDelayMinutes += ONE_DAY
            }
            if (initialDelayMinutes < FLEX_MINUTES) {
                createNotificationWork(
                    id = lesson.id,
                    initialDelayMinutes = initialDelayMinutes - FLEX_MINUTES
                ).startWork(WORK_NAME + lesson.id)
            }
        }
    }

    private fun OneTimeWorkRequest.startWork(workName: String) {
        workManager.enqueueUniqueWork(
            workName,
            ExistingWorkPolicy.REPLACE,
            this
        )
    }

    private fun createNotificationWork(
        id: Long,
        initialDelayMinutes: Long
    ): OneTimeWorkRequest {
        val data = Data.Builder()
            .putLong(TimeBaseNotificationWorker.LESSON_ID, id)
            .build()
        val builder = OneTimeWorkRequestBuilder<TimeBaseNotificationWorker>()
            .setInitialDelay(initialDelayMinutes, TimeUnit.MINUTES)
            .setInputData(data)
        return builder.build()
    }

    private fun createUpdateWork(): OneTimeWorkRequest =
        OneTimeWorkRequestBuilder<GenerateTimeBaseWorksWorker>()
            .setInitialDelay(getTimeBeforeNextDayUseCase(), TimeUnit.MILLISECONDS)
            .build()
}