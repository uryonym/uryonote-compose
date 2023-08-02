package com.uryonym.uryonote.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteEditScreen(
    onNavigateBack: () -> Unit,
    viewModel: NoteEditViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column {
        Text(text = "ノート編集")
        TextField(value = uiState.title, onValueChange = viewModel::onTitleChange)
        TextField(value = uiState.content, onValueChange = viewModel::onContentChange)
        Button(onClick = viewModel::onUpdateNote) {
            Text(text = "保存")
        }
        Button(onClick = onNavigateBack) {
            Text(text = "戻る")
        }
    }
}