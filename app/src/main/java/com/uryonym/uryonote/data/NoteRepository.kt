package com.uryonym.uryonote.data

import com.uryonym.uryonote.data.local.Note
import com.uryonym.uryonote.data.local.NoteDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

interface NoteRepository {
    fun getNotes(): Flow<List<Note>>

    fun getNote(noteId: String): Flow<Note>

    suspend fun insertNote(title: String)

    suspend fun updateNote(note: Note)

    suspend fun deleteNote(note: Note)
}

@Singleton
class NoteRepositoryImpl @Inject constructor(
    private val localDataSource: NoteDao
) : NoteRepository {
    override fun getNotes(): Flow<List<Note>> {
        return localDataSource.getNotes()
    }

    override fun getNote(noteId: String): Flow<Note> {
        return localDataSource.getNote(noteId)
    }

    override suspend fun insertNote(title: String) {
        val note = Note(
            id = "testid",
            title = title,
            content = ""
        )
        localDataSource.insertNote(note)
    }

    override suspend fun updateNote(note: Note) {
        localDataSource.updateNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        localDataSource.deleteNote(note)
    }
}
