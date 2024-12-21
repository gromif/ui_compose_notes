package com.nevidimka655.notes.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.nevidimka655.notes.data.paging.NotesPagingProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotesListViewModel @Inject constructor(
    notesPagingProvider: NotesPagingProvider
) : ViewModel() {
    val notesPaging = notesPagingProvider.createPagingSource().cachedIn(viewModelScope)

}