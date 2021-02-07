package ru.fevgenson.timetable.shared.notifications.ui.workers

import android.content.Context
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.runBlocking
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.get
import ru.fevgenson.timetable.shared.notifications.domain.usecase.GetNotificationLessonUseCase
import ru.fevgenson.timetable.shared.notifications.ui.builders.ChannelBuilder
import ru.fevgenson.timetable.shared.notifications.ui.builders.NotificationBuilder
import ru.fevgenson.timetable.shared.notifications.ui.channels.Channels

class TimeBaseNotificationWorker(
    private val context: Context,
    private val workerParams: WorkerParameters
) : Worker(context, workerParams) {

    companion object {

        const val LESSON_ID = "LESSON_ID"
        private const val DEFAULT_VALUE = -1L
        private const val NOTIFICATION_ID = 15
    }


    private val getLessonUseCase = get(GetNotificationLessonUseCase::class.java)
    private val channelBuilder = get(ChannelBuilder::class.java)
    private val notificationBuilder =
        get(NotificationBuilder::class.java) { parametersOf(Channels.TIME_BASE_CHANNEL) }

    override fun doWork(): Result = runBlocking {

        channelBuilder.createChannel(Channels.TIME_BASE_CHANNEL)
        val lessonId = workerParams.inputData.getLong(LESSON_ID, DEFAULT_VALUE)
        val lesson = getLessonUseCase(lessonId)
        val notification = notificationBuilder.buildLessonNotification(lesson, false)
        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, notification)
        Result.success()
    }
}