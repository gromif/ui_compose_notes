package com.nevidimka655.notes.data.util

import com.nevidimka655.astracrypt.notes.db.NoteItemEntity

class AeadHandler(
    private val aeadUtil: AeadUtil,
) {

    suspend fun encryptNoteEntity(aeadIndex: Int, data: NoteItemEntity) = data.copy(
        name = encrypt(aeadIndex, data.name),
        text = encrypt(aeadIndex, data.text),
        textPreview = encrypt(aeadIndex, data.textPreview),
    )

    suspend fun decryptNoteEntity(aeadIndex: Int, data: NoteItemEntity) = data.copy(
        name = encrypt(aeadIndex, data.name),
        text = encrypt(aeadIndex, data.text),
        textPreview = encrypt(aeadIndex, data.textPreview),
    )

    private suspend fun decrypt(aeadIndex: Int, data: String?): String? {
        return data?.let {
            aeadUtil.decrypt(aeadIndex = aeadIndex, data)
        }
    }

    private suspend fun encrypt(aeadIndex: Int, data: String?): String? {
        return data?.let {
            aeadUtil.encrypt(aeadIndex = aeadIndex, data)
        }
    }

}