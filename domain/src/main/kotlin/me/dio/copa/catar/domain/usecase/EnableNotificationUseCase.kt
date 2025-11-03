package me.dio.copa.catar.domain.usecase

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import dagger.hilt.android.qualifiers.ApplicationContext
import me.dio.copa.catar.domain.model.MatchDomain
import me.dio.copa.catar.domain.repositories.MatchesRepository
import me.dio.copa.catar.worker.NotificationMatcherWorker
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class EnableNotificationUseCase @Inject constructor(
    private val repository: MatchesRepository,
    private val workManager: WorkManager,
    @ApplicationContext private val context: Context
) {
    suspend operator fun invoke(match: MatchDomain) {
  
        repository.enableNotificationFor(match.id)

        val data = workDataOf("matchName" to match.name)

        val request = OneTimeWorkRequestBuilder<NotificationMatcherWorker>()
            .setInputData(data)
            .setInitialDelay(10, TimeUnit.SECONDS) 
            .build()

        workManager.enqueue(request)
    }
}
