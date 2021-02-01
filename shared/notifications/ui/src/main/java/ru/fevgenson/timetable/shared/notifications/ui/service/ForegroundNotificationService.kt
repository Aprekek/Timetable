package ru.fevgenson.timetable.shared.notifications.ui.service

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.job
import org.koin.android.ext.android.get
import org.koin.core.parameter.parametersOf
import ru.fevgenson.timetable.shared.notifications.domain.entities.NotificationLessonEntity
import ru.fevgenson.timetable.shared.notifications.domain.scenario.GetNotEndNotificationLessonsScenario
import ru.fevgenson.timetable.shared.notifications.domain.usecase.GetNotificationLessonsFlowUseCase
import ru.fevgenson.timetable.shared.notifications.ui.builders.ChannelBuilder
import ru.fevgenson.timetable.shared.notifications.ui.builders.NotificationBuilder
import ru.fevgenson.timetable.shared.notifications.ui.channels.Channels
import ru.fevgenson.timetable.shared.timeutils.domain.usecase.GetCurrentDayUseCase
import ru.fevgenson.timetable.shared.timeutils.domain.usecase.GetCurrentWeekTypeUseCase
import ru.fevgenson.timetable.shared.timeutils.ui.broadcastreceivers.DateBroadcastReceiver
import ru.fevgenson.timetable.shared.timeutils.ui.broadcastreceivers.MinutesBroadcastReceiver

class ForegroundNotificationService : LifecycleService() {

    companion object {
        private const val FOREGROUND_ID = 215
        private var serviceInstanceRunning = false

        fun startService(context: Context) {
            if (!serviceInstanceRunning) {
                val intent = Intent(context, ForegroundNotificationService::class.java)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    context.startForegroundService(intent)
                } else {
                    context.startService(intent)
                }
            }
        }

        fun stopService(context: Context) {
            if (serviceInstanceRunning) {
                context.stopService(Intent(context, ForegroundNotificationService::class.java))
            }
        }
    }

    private var isLessonShowed = true

    private val getNotificationLessonsFlowUseCase: GetNotificationLessonsFlowUseCase = get()
    private val getNotEndNotificationLessonsScenario: GetNotEndNotificationLessonsScenario = get()

    private val getCurrentWeekTypeUseCase: GetCurrentWeekTypeUseCase = get()
    private val getCurrentDayUseCase: GetCurrentDayUseCase = get()

    private val channelBuilder: ChannelBuilder = get()
    private val notificationBuilder: NotificationBuilder =
        get { parametersOf(Channels.FOREGROUND_CHANNEL) }

    private val dateBroadcastReceiver: DateBroadcastReceiver = get()
    private val minutesBroadcastReceiver: MinutesBroadcastReceiver = get()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        serviceInstanceRunning = true
        channelBuilder.createChannel(Channels.FOREGROUND_CHANNEL)
        startForeground(FOREGROUND_ID, notificationBuilder.buildInitNotification())
        registerReceivers()
        initObserve()
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceivers()
        serviceInstanceRunning = false
    }

    private fun registerReceivers() {
        registerReceiver(
            dateBroadcastReceiver,
            DateBroadcastReceiver.dateBroadcastReceiverIntentFilter
        )
        registerReceiver(
            minutesBroadcastReceiver,
            MinutesBroadcastReceiver.minutesBroadcastReceiverIntentFilter
        )
    }

    private fun unregisterReceivers() {
        unregisterReceiver(dateBroadcastReceiver)
        unregisterReceiver(minutesBroadcastReceiver)
    }

    private fun initObserve() {
        val job = SupervisorJob(lifecycle.coroutineScope.coroutineContext.job)
        val coroutineScope = CoroutineScope(job)

        val currentDay = getCurrentDayUseCase()
        val currentWeekType = getCurrentWeekTypeUseCase()

        getNotificationLessonsFlowUseCase(currentWeekType, currentDay).combine(
            minutesBroadcastReceiver.callbacksFlow
        ) { lessons: List<NotificationLessonEntity>, _: Int ->
            val notEndLessons = getNotEndNotificationLessonsScenario(lessons)
            updateNotification(notEndLessons.firstOrNull())
        }.launchIn(coroutineScope)

        dateBroadcastReceiver.callbacksFlow.drop(1).onEach {
            coroutineScope.cancel()
            initObserve()
        }.launchIn(coroutineScope)
    }

    private fun updateNotification(currentLesson: NotificationLessonEntity?) {
        isLessonShowed = when {
            currentLesson == null && !isLessonShowed -> return
            currentLesson == null -> false
            else -> true
        }
        val notification = currentLesson?.let(notificationBuilder::buildLessonNotification)
            ?: notificationBuilder.buildEmptyNotification()
        updateNotification(notification)
    }

    private fun updateNotification(notification: Notification) {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
        notificationManager?.notify(FOREGROUND_ID, notification)
    }
}