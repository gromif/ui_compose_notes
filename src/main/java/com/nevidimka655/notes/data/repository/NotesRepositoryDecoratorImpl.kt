package com.nevidimka655.notes.data.repository

import androidx.paging.PagingSource
import com.nevidimka655.notes.data.database.NoteItemEntity
import com.nevidimka655.notes.data.database.tuples.TransformNotesTuple
import com.nevidimka655.notes.data.mappers.NoteItemEntityMapper
import com.nevidimka655.notes.domain.model.Note
import com.nevidimka655.notes.domain.repository.NotesRepository
import com.nevidimka655.notes.domain.repository.NotesRepositoryDecorator

class NotesRepositoryDecoratorImpl(
    private val notesRepository: NotesRepository,
    private val noteItemEntityMapper: NoteItemEntityMapper
) : NotesRepositoryDecorator {
    override suspend fun deleteById(id: Long) {
        notesRepository.deleteById(id = id)
    }

    override suspend fun update(id: Long, name: String, text: String) {
        notesRepository.update(id = id, name = name, text = text)
    }

    override suspend fun insert(name: String, text: String) {
        notesRepository.insert(name = name, text = text)
    }

    override suspend fun getById(id: Long): Note {
        val noteItemEntity = notesRepository.getById(id = id)
        return noteItemEntityMapper(item = noteItemEntity)
    }

    override suspend fun updateTransform(transformNotesTuple: TransformNotesTuple) {
        notesRepository.updateTransform(transformNotesTuple = transformNotesTuple)
    }

    override suspend fun getTransformItems(
        pageSize: Int,
        pageIndex: Int
    ): List<TransformNotesTuple> {
        return notesRepository.getTransformItems(pageSize = pageSize, pageIndex = pageIndex)
    }

    override fun listOrderDescAsc(): PagingSource<Int, NoteItemEntity> {
        return notesRepository.listOrderDescAsc()
    }
}