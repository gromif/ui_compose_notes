package com.nevidimka655.notes.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkManager
import com.nevidimka655.domain.notes.model.AeadMode
import com.nevidimka655.domain.notes.usecase.GetAeadPreferenceFlowUseCase
import com.nevidimka655.notes.settings.work.UpdateAeadWorker
import dagger.hilt.android.lifecycle.HiltViewModel
import io.gromif.astracrypt.utils.dispatchers.IoDispatcher
import io.gromif.crypto.tink.extensions.getTemplateList
import io.gromif.crypto.tink.model.KeysetTemplates
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class AeadSettingsViewModel @Inject constructor(
    @IoDispatcher
    private val defaultDispatcher: CoroutineDispatcher,
    private val workManager: WorkManager,
    getAeadPreferenceFlowUseCase: GetAeadPreferenceFlowUseCase,
) : ViewModel() {
    val aeadTemplatesList = KeysetTemplates.AEAD.getTemplateList()
    val aeadPreference = getAeadPreferenceFlowUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    fun setAeadPreference(targetAead: AeadMode) = viewModelScope.launch(defaultDispatcher) {
        val data = UpdateAeadWorker.createWorkerData(targetAead = targetAead)
        val workerRequest = OneTimeWorkRequestBuilder<UpdateAeadWorker>().apply {
            setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            setInputData(data)
        }.build()
        workManager.enqueue(workerRequest)
    }

}