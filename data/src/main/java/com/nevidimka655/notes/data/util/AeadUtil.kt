package com.nevidimka655.notes.data.util

import com.google.crypto.tink.Aead
import io.gromif.crypto.tink.core.encoders.Base64Encoder
import io.gromif.crypto.tink.data.AeadManager
import io.gromif.crypto.tink.data.AssociatedDataManager
import io.gromif.crypto.tink.extensions.decodeAndDecrypt
import io.gromif.crypto.tink.extensions.encryptAndEncode
import io.gromif.crypto.tink.model.KeysetTemplates
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class AeadUtil(
    private val aeadManager: AeadManager,
    private val associatedDataManager: AssociatedDataManager,
    private val base64Encoder: Base64Encoder,
) {
    private val mutex = Mutex()
    private val cachedAeadMap = hashMapOf<Int, Aead>()

    suspend fun decrypt(aeadIndex: Int, data: String): String {
        val aead = getCachedAead(aeadIndex = aeadIndex)
        return aead.decodeAndDecrypt(
            value = data,
            associatedData = associatedDataManager.getAssociatedData(),
            encoder = base64Encoder
        )
    }

    suspend fun encrypt(aeadIndex: Int, data: String): String {
        val aead = getCachedAead(aeadIndex = aeadIndex)
        return aead.encryptAndEncode(
            value = data,
            associatedData = associatedDataManager.getAssociatedData(),
            encoder = base64Encoder
        )
    }

    private suspend fun getCachedAead(aeadIndex: Int): Aead = mutex.withLock {
        cachedAeadMap[aeadIndex] ?: aeadManager.aead(
            tag = TAG_KEYSET,
            keyParams = KeysetTemplates.AEAD.entries[aeadIndex].params
        )
    }

}

private const val TAG_KEYSET = "notes_db"