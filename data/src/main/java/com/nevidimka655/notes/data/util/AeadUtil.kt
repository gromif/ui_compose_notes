package com.nevidimka655.notes.data.util

import com.google.crypto.tink.Aead
import io.gromif.crypto.tink.core.encoders.Base64Encoder
import io.gromif.crypto.tink.data.AeadManager
import io.gromif.crypto.tink.data.AssociatedDataManager
import io.gromif.crypto.tink.model.KeysetTemplates
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class AeadUtil(
    private val aeadManager: AeadManager,
    private val associatedDataManager: AssociatedDataManager,
    private val base64Encoder: Base64Encoder
) {
    private val mutex = Mutex()
    private val cachedEncryptionAeadMap = hashMapOf<Int, Aead>()
    private val cachedDecryptionAeadMap = hashMapOf<Int, Aead>()

    suspend fun decrypt(aeadIndex: Int, data: String): String {
        val encryptedBytes = base64Encoder.decode(data)
        val aead = getCachedAead(
            cachedMap = cachedDecryptionAeadMap,
            aeadIndex = aeadIndex
        )
        val decryptedBytes = aead.decrypt(
            /* ciphertext = */ encryptedBytes,
            /* associatedData = */ associatedDataManager.getAssociatedData()
        )
        return decryptedBytes.decodeToString()
    }

    suspend fun encrypt(aeadIndex: Int, data: String): String {
        val aead = getCachedAead(
            cachedMap = cachedEncryptionAeadMap,
            aeadIndex = aeadIndex
        )
        val encryptedBytes = aead.encrypt(
            /* plaintext = */ data.toByteArray(),
            /* associatedData = */ associatedDataManager.getAssociatedData()
        )
        return base64Encoder.encode(encryptedBytes)
    }

    private suspend fun getCachedAead(
        cachedMap: HashMap<Int, Aead>,
        aeadIndex: Int,
    ): Aead = mutex.withLock {
        val cachedAead = cachedMap[aeadIndex]
        cachedAead ?: getAead(aeadIndex = aeadIndex).also {
            cachedMap[aeadIndex] = it
        }
    }

    private suspend fun getAead(aeadIndex: Int): Aead {
        return aeadManager.aead(
            tag = TAG_KEYSET,
            keyParams = KeysetTemplates.AEAD.entries[aeadIndex].params
        )
    }

}

private const val TAG_KEYSET = "notes_db"