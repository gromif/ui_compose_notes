package com.nevidimka655.notes.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.nevidimka655.domain.notes.usecase.GetPagingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class NotesListViewModel @Inject constructor(
    getPagingUseCase: GetPagingUseCase
) : ViewModel() {
    val notesPaging = getPagingUseCase().cachedIn(viewModelScope)

}