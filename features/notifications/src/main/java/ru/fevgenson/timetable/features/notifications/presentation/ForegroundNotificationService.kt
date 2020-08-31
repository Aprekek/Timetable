package ru.fevgenson.timetable.features.notifications.presentation

import android.content.Intent
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.LiveData
import org.koin.android.ext.android.get
import ru.fevgenson.timetable.features.notifications.R
import ru.fevgenson.timetable.features.notifications.domain.entities.NotificationLesson
import ru.fevgenson.timetable.features.notifications.domain.usecase.GetLessonsUseCase
import ru.fevgenson.timetable.features.notifications.domain.utils.getCurrentLesson
import ru.fevgenson.timetable.libraries.core.utils.broadcastrecivers.DateBroadcastReceiver
import ru.fevgenson.timetable.libraries.core.utils.broadcastrecivers.MinutesBroadcastReceiver
import ru.fevgenson.timetable.libraries.core.utils.dateutils.DateUtils

class ForegroundNotificationService : LifecycleService() {

    private companion object {
        const val CHANNEL_ID = "TIMETABLE_FOREGROUND_CHANNEL_ID"
        const val FOREGROUND_ID = 215
    }

    private var isLessonShowed = true

    private val getLessonsUseCase: GetLessonsUseCase = get()
    private val dateBroadcastReceiver: DateBroadcastReceiver = get()
    private val minutesBroadcastReceiver: MinutesBroadcastReceiver = get()

    private var lessons: LiveData<List<NotificationLesson>> =
        getLessonsUseCase(DateUtils.getCurrentWeek(), DateUtils.getCurrentDay())

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        NotificationUtils.createChannel(
            channelId = CHANNEL_ID,
            channelName = getString(R.string.notification_foreground_channel_id),
            context = this
        )
        startForeground(FOREGROUND_ID, NotificationUtils.buildInitNotification(this, CHANNEL_ID))
        registerReceivers()
        initObserve()
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceivers()
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
        val lifecycleOwner = LifecycleOwner { lifecycle }
        lessons.observe(lifecycleOwner) {
            showLessonNotification(it.getCurrentLesson())
        }
        minutesBroadcastReceiver.callbacks.observe(lifecycleOwner) {
            showLessonNotification(lessons.value?.getCurrentLesson())
        }
        dateBroadcastReceiver.callbacks.observe(lifecycleOwner) { weekAndDay ->
            lessons.removeObservers(lifecycleOwner)
            lessons = getLessonsUseCase(weekAndDay.weekType, weekAndDay.day)
            lessons.observe(lifecycleOwner) {
                showLessonNotification(it.getCurrentLesson())
            }
        }
    }

    private fun showLessonNotification(currentLesson: NotificationLesson?) {
        isLessonShowed = when {
            currentLesson == null && !isLessonShowed -> return
            currentLesson == null -> false
            else -> true
        }
        val notification = NotificationUtils.buildLessonNotification(
            context = this,
            channel = CHANNEL_ID,
            notificationLesson = currentLesson
        )
        NotificationUtils.updateNotification(
            notificationId = FOREGROUND_ID,
            context = this,
            notification = notification
        )
    }
}