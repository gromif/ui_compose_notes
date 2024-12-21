package com.nevidimka655.notes.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.nevidimka655.notes.Notes
import com.nevidimka655.notes.domain.model.Note
import com.nevidimka655.notes.ui.shared.Note
import com.nevidimka655.ui.compose_core.theme.spaces

@Composable
fun Notes.NotesListScreen(
    noteItems: LazyPagingItems<Note>,
    onClick: (id: Long) -> Unit
) = LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(MaterialTheme.spaces.spaceMedium),
    verticalArrangement = Arrangement.spacedBy(MaterialTheme.spaces.spaceMedium)
) {
    items(
        count = noteItems.itemSnapshotList.size,
        key = { noteItems[it]?.id ?: it }
    ) { index ->
        noteItems[index]?.let {
            Note(
                title = it.name,
                summary = it.textPreview,
                date = it.creationTime
            ) {
                onClick(it.id)
            }
        }
    }
}