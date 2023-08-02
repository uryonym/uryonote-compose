package com.uryonym.uryonote.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavHostScreen() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavigationRoute.NOTE_LIST_ROUTE
    ) {
        composable(route = NavigationRoute.NOTE_LIST_ROUTE) {
            NoteListScreen(
                onNavigateAdd = { navController.navigate(NavigationRoute.NOTE_ADD_ROUTE) },
                onNavigateEdit = { navController.navigate(NavigationRoute.NOTE_EDIT_ROUTE) }
            )
        }
        composable(route = NavigationRoute.NOTE_ADD_ROUTE) {
            Column {
                Text(text = "ノート追加")
                Button(onClick = { navController.popBackStack() }) {
                    Text(text = "戻る")
                }
            }
        }
        composable(route = NavigationRoute.NOTE_EDIT_ROUTE) {
            Column {
                Text(text = "ノート編集")
                Button(onClick = { navController.popBackStack() }) {
                    Text(text = "戻る")
                }
            }
        }
    }
}

class NavigationRoute {
    companion object {
        val NOTE_LIST_ROUTE = "note_list_route"
        val NOTE_ADD_ROUTE = "note_add_route"
        val NOTE_EDIT_ROUTE = "note_edit_route"
    }
}