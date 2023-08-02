package com.uryonym.uryonote.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
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
        TextField(
            value = uiState.title,
            onValueChange = viewModel::onTitleChange,
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = uiState.content,
            onValueChange = viewModel::onContentChange,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8F)
        )
        Row {
            Button(onClick = {
                viewModel.onUpdateNote()
                onNavigateBack()
            }) {
                Text(text = "保存")
            }
            Button(onClick = {
                viewModel.onDeleteNote()
                onNavigateBack()
            }) {
                Text(text = "削除")
            }
        }
        Button(onClick = onNavigateBack) {
            Text(text = "戻る")
        }
    }
}