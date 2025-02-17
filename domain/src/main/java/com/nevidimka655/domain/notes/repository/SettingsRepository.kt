package com.nevidimka655.domain.notes.repository

import com.nevidimka655.domain.notes.model.AeadMode
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    fun getAeadModeFlow(): Flow<AeadMode>

    suspend fun getAeadMode(): AeadMode

    suspend fun setAeadMode(aeadMode: AeadMode)

}