package com.nevidimka655.notes.data.mappers

import android.text.format.DateFormat
import com.nevidimka655.domain.notes.model.Note
import com.nevidimka655.domain.notes.model.NoteState
import com.nevidimka655.notes.data.db.NoteItemEntity
import io.gromif.astracrypt.utils.Mapper

private const val TIME_PATTERN = "d MMMM yyyy"

class NoteMapper: Mapper<NoteItemEntity, Note> {

    override fun invoke(item: NoteItemEntity): Note {
        return Note(
            id = item.id,
            name = item.name ?: "",
            text = item.text ?: "",
            textPreview = item.textPreview?.run { "$this..." } ?: "",
            state = NoteState.entries[item.state],
            creationTime = DateFormat.format(TIME_PATTERN, item.creationTime).toString()
        )
    }

}