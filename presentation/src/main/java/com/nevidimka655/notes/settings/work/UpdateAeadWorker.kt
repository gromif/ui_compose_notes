package com.nevidimka655.notes.settings.work

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.ServiceInfo
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.nevidimka655.astracrypt.resources.R
import com.nevidimka655.domain.notes.model.AeadMode
import com.nevidimka655.domain.notes.usecase.UpdateNotesAeadUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.gromif.astracrypt.utils.Api
import io.gromif.astracrypt.utils.dispatchers.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlin.random.Random

@HiltWorker
internal class UpdateAeadWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    @IoDispatcher
    private val defaultDispatcher: CoroutineDispatcher,
    private val updateNotesAeadUseCase: UpdateNotesAeadUseCase,
) : CoroutineWorker(context, params) {

    companion object {
        const val TARGET_AEAD = "a1"
    }

    override suspend fun doWork(): Result {
        val targetAeadTemplate = inputData.getInt(TARGET_AEAD, -1).let {
            if (it == -1) AeadMode.None else AeadMode.Template(id = it, name = "")
        }
        setForeground(getForegroundInfo())
        withContext(defaultDispatcher.limitedParallelism(4)) {
            updateNotesAeadUseCase(targetAeadTemplate)
        }
        return Result.success()
    }

    override suspend fun getForegroundInfo(): ForegroundInfo {
        val title = applicationContext.getString(R.string.notification_dbTransform_title)
        val cancelText = applicationContext.getString(android.R.string.cancel)
        // This PendingIntent can be used to cancel the worker
        val workerStopPendingIntent =
            WorkManager.getInstance(applicationContext).createCancelPendingIntent(id)
        // Create a Notification channel if necessary
        if (Api.atLeast8()) createChannel()
        val notification = NotificationCompat.Builder(
            applicationContext, NOTIFICATION_CHANNEL_ID
        ).apply {
            setContentTitle(title)
            setTicker(title)
            setProgress(100, 0, true)
            setSmallIcon(R.drawable.ic_notification_app_icon)
            setOngoing(true)
            setSilent(true)
            addAction(R.drawable.ic_close, cancelText, workerStopPendingIntent)
        }.build()
        val notificationId = Random.nextInt()
        return if (Api.atLeast10()) ForegroundInfo(
            notificationId,
            notification,
            ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC
        ) else ForegroundInfo(notificationId, notification)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel() {
        val name = applicationContext.getString(R.string.notification_channel_fileOperations)
        val summary =
            applicationContext.getString(R.string.notification_channel_fileOperations_desc)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(NOTIFICATION_CHANNEL_ID, name, importance).apply {
            description = summary
        }
        // Register the channel with the system
        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}

private const val NOTIFICATION_CHANNEL_ID = "file_operations_channel"