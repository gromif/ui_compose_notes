package com.nevidimka655.notes.data.repository

import com.nevidimka655.astracrypt.notes.db.NoteItemEntity
import com.nevidimka655.astracrypt.notes.db.NotesDao
import com.nevidimka655.domain.notes.model.Note
import com.nevidimka655.domain.notes.repository.Repository
import io.gromif.astracrypt.utils.Mapper

class RepositoryImpl(
    private val dao: NotesDao,
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

    override suspend fun update(id: Long, name: String, text: String) {
        val noteItemEntity = createNoteItemEntity(id = id, name, text)
        dao.update(noteItemEntity = noteItemEntity)
    }

    override suspend fun insert(name: String, text: String) {
        val noteItemEntity = createNoteItemEntity(name = name, text = text)
        dao.insert(noteItemEntity = noteItemEntity)
    }

    override suspend fun getById(id: Long): Note {
        val noteItemEntity = dao.getById(id = id)
        return noteMapper(item = noteItemEntity)
    }

    override suspend fun getByPage(
        pageSize: Int, pageIndex: Int
    ): List<Note> {
        //return dao.getTransformItems(pageSize, pageIndex)
        TODO("DatabaseTransform")
    }
}