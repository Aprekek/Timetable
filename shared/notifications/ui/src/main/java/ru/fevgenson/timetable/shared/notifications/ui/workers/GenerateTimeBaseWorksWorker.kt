package ru.fevgenson.timetable.shared.notifications.ui.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.runBlocking
import org.koin.java.KoinJavaComponent.get
import ru.fevgenson.timetable.shared.notifications.ui.schedulers.TimeBaseNotificationScheduler

class GenerateTimeBaseWorksWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    private val timeBaseNotificationScheduler = get(TimeBaseNotificationScheduler::class.java)

    override fun doWork(): Result = runBlocking {
        timeBaseNotificationScheduler.createTasks(this)
        Result.success()
    }
}