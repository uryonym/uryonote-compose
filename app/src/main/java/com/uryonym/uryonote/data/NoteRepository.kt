package com.uryonym.uryonote.data

import com.uryonym.uryonote.data.local.Note
import com.uryonym.uryonote.data.local.NoteDao
import com.uryonym.uryonote.data.network.NoteApi
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

interface NoteRepository {
    fun getNotes(): Flow<List<Note>>

    suspend fun getNote(noteId: String): Note

    suspend fun insertNote(title: String)

    suspend fun updateNote(noteId: String, title: String, content: String)

    suspend fun deleteNote(noteId: String)

    suspend fun refreshNotes()
}

@Singleton
class NoteRepositoryImpl @Inject constructor(
    private val localDataSource: NoteDao
) : NoteRepository {
    override fun getNotes(): Flow<List<Note>> {
        return localDataSource.getNotes()
    }

    override suspend fun getNote(noteId: String): Note {
        return localDataSource.getNote(noteId)
    }

    override suspend fun insertNote(title: String) {
        val note = Note(
            id = UUID.randomUUID().toString(),
            title = title,
            content = "test"
        )
        localDataSource.insertNote(note)
        NoteApi.retrofitService.addNote(note.toNetwork())
    }

    override suspend fun updateNote(noteId: String, title: String, content: String) {
        val note = Note(
            id = noteId,
            title = title,
            content = content
        )
        localDataSource.updateNote(note)
        NoteApi.retrofitService.editNote(noteId, note.toNetwork())
    }

    override suspend fun deleteNote(noteId: String) {
        localDataSource.deleteNote(noteId)
        NoteApi.retrofitService.deleteNote(noteId)
    }

    override suspend fun refreshNotes() {
        val notes = NoteApi.retrofitService.getNotes().toLocal()
        localDataSource.deleteAllNotes()
        notes.map {
            localDataSource.insertNote(it)
        }
    }
}
