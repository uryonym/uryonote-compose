package com.uryonym.uryonote.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteListScreen(
    onNavigateAdd: () -> Unit,
    onNavigateEdit: () -> Unit,
    viewModel: NoteListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column {
        Text(text = "ノート一覧")
        Button(onClick = onNavigateAdd) {
            Text(text = "追加画面に遷移")
        }
        Button(onClick = onNavigateEdit) {
            Text(text = "編集画面に遷移")
        }

        Row {
            TextField(value = uiState.title, onValueChange = viewModel::onTitleChange)
            Button(onClick = viewModel::onCreateNote) {
                Text(text = "作成")
            }
        }

        LazyColumn() {
            items(items = uiState.notes) { note ->
                Column {
                    Text(text = note.title)
                    Divider()
                }
            }
        }
    }
}