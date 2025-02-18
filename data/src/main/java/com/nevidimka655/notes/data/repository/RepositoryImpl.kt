package com.nevidimka655.notes.data.repository

import com.nevidimka655.astracrypt.notes.db.NoteItemEntity
import com.nevidimka655.astracrypt.notes.db.NotesDao
import com.nevidimka655.domain.notes.model.AeadMode
import com.nevidimka655.domain.notes.model.Note
import com.nevidimka655.domain.notes.repository.Repository
import com.nevidimka655.notes.data.util.AeadHandler
import io.gromif.astracrypt.utils.Mapper
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class RepositoryImpl(
    private val dao: NotesDao,
    private val aeadHandler: AeadHandler,
    private val noteMapper: Mapper<NoteItemEntity, Note>
) : Repository {
    override suspend fun deleteById(id: Long) {
        dao.deleteById(id = id)
    }

    private fun createNoteItemEntity(id: Long = 0L, name: String, text: String): NoteItemEntity {
        val trimmedName = name.trim().ifEmpty { null }
        val trimmedText = text.trim()
        val textPreview = if (trimmedText.isNotEmpty()) trimmedText.take(80) else null
        return NoteItemEntity(
            id = id,
            name = trimmedName,
            text = trimmedText,
            textPreview = textPreview,
            creationTime = System.currentTimeMillis()
        )
    }

    override suspend fun update(
        aead: AeadMode,
        id: Long,
        name: String,
        text: String,
    ) {
        val noteItemEntity = createNoteItemEntity(id = id, name, text).let {
            if (aead is AeadMode.Template) aeadHandler.encryptNoteEntity(aead, it) else it
        }
        dao.update(noteItemEntity = noteItemEntity)
    }

    override suspend fun insert(aead: AeadMode, name: String, text: String) {
        val noteItemEntity = createNoteItemEntity(name = name, text = text).let {
            if (aead is AeadMode.Template) aeadHandler.encryptNoteEntity(aead, it) else it
        }
        dao.insert(noteItemEntity = noteItemEntity)
    }

    override suspend fun getById(aead: AeadMode, id: Long): Note {
        val noteItemEntity = dao.getById(id = id).let {
            if (aead is AeadMode.Template) aeadHandler.decryptNoteEntity(aead, it) else it
        }
        return noteMapper(item = noteItemEntity)
    }

    override suspend fun changeAead(targetAeadMode: AeadMode) = coroutineScope {
        val pageSize = 10
        var offset = 0
        var page = dao.getTransformItems(pageSize, offset).also { offset += pageSize }
        while (page.isNotEmpty()) {
            page.forEach {
                if (targetAeadMode is AeadMode.Template) launch {
                    val transformedTuple = aeadHandler.encryptTransformTuple(
                        aeadIndex = targetAeadMode,
                        data = it
                    )
                    dao.updateTransform(transformedTuple)
                } else launch { dao.updateTransform(it) }
            }
            page = dao.getTransformItems(pageSize, offset).also { offset += pageSize }
        }
    }

}