package com.nevidimka655.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nevidimka655.domain.notes.model.Note
import com.nevidimka655.domain.notes.usecase.GetNotesListFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class NotesListViewModel @Inject constructor(
    getNotesListFlow: GetNotesListFlow<PagingData<Note>>
) : ViewModel() {
    val notesPaging = getNotesListFlow().cachedIn(viewModelScope)

}