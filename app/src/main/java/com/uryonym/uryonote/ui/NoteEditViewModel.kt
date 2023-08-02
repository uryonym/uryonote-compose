package com.uryonym.uryonote.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uryonym.uryonote.data.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class NoteEditUiState(
    val noteId: String = "",
    val title: String = "",
    val content: String = ""
)

@HiltViewModel
class NoteEditViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val noteRepository: NoteRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<NoteEditUiState>(NoteEditUiState())
    val uiState: StateFlow<NoteEditUiState> = _uiState

    init {
        _uiState.update {
            it.copy(noteId = savedStateHandle["noteId"]!!)
        }

        viewModelScope.launch {
            noteRepository.getNote(uiState.value.noteId).let { note ->
                _uiState.update {
                    it.copy(title = note.title, content = note.content)
                }
            }
        }
    }

    fun onTitleChange(title: String) {
        _uiState.update {
            it.copy(title = title)
        }
    }

    fun onContentChange(content: String) {
        _uiState.update {
            it.copy(content = content)
        }
    }

    fun onUpdateNote() {
        viewModelScope.launch {
            noteRepository.updateNote(
                noteId = uiState.value.noteId,
                title = uiState.value.title,
                content = uiState.value.content
            )
        }
    }

    fun onDeleteNote() {
        viewModelScope.launch {  }
    }
}
