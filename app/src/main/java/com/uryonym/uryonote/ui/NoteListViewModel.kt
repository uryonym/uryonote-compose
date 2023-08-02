package com.uryonym.uryonote.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uryonym.uryonote.data.NoteRepository
import com.uryonym.uryonote.data.local.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class NoteListUiState(
    val notes: List<Note> = emptyList(),
    val title: String = ""
)

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<NoteListUiState>(NoteListUiState())
    val uiState: StateFlow<NoteListUiState> = _uiState

    init {
        viewModelScope.launch {
            noteRepository.getNotes().collect { notes ->
                _uiState.update {
                    it.copy(notes = notes)
                }
            }
        }
    }

    fun onTitleChange(title: String) {
        _uiState.update {
            it.copy(title = title)
        }
    }

    fun onCreateNote() {
        viewModelScope.launch {
            noteRepository.insertNote(title = uiState.value.title)
        }
    }
}