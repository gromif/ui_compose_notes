package com.nevidimka655.notes.data.util

import com.nevidimka655.astracrypt.notes.db.NoteItemEntity
import com.nevidimka655.astracrypt.notes.db.tuples.TransformNotesTuple
import com.nevidimka655.domain.notes.model.AeadMode

class AeadHandler(
    private val aeadUtil: AeadUtil,
) {

    suspend fun encryptNoteEntity(aeadIndex: AeadMode.Template, data: NoteItemEntity) = data.copy(
        name = encrypt(aeadIndex, data.name),
        text = encrypt(aeadIndex, data.text),
        textPreview = encrypt(aeadIndex, data.textPreview),
    )

    suspend fun encryptTransformTuple(
        aeadIndex: AeadMode.Template,
        data: TransformNotesTuple
    ) = data.copy(
        name = encrypt(aeadIndex, data.name),
        text = encrypt(aeadIndex, data.text),
        textPreview = encrypt(aeadIndex, data.textPreview),
    )

    suspend fun decryptTransformTuple(
        aeadTemplate: AeadMode.Template,
        data: TransformNotesTuple
    ) = data.copy(
        name = decrypt(aeadTemplate, data.name),
        text = decrypt(aeadTemplate, data.text),
        textPreview = decrypt(aeadTemplate, data.textPreview),
    )

    suspend fun decryptNoteEntity(aeadIndex: AeadMode.Template, data: NoteItemEntity) = data.copy(
        name = decrypt(aeadIndex, data.name),
        text = decrypt(aeadIndex, data.text),
        textPreview = decrypt(aeadIndex, data.textPreview),
    )

    private suspend fun decrypt(aeadIndex: AeadMode.Template, data: String?): String? {
        return data?.let {
            aeadUtil.decrypt(aeadIndex = aeadIndex.id, data)
        }
    }

    private suspend fun encrypt(aeadIndex: AeadMode.Template, data: String?): String? {
        return data?.let {
            aeadUtil.encrypt(aeadIndex = aeadIndex.id, data)
        }
    }

}