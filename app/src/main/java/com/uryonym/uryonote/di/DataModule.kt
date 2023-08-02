package com.uryonym.uryonote.di

import android.content.Context
import androidx.room.Room
import com.uryonym.uryonote.data.NoteRepository
import com.uryonym.uryonote.data.NoteRepositoryImpl
import com.uryonym.uryonote.data.local.NoteDao
import com.uryonym.uryonote.data.local.NoteDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindNoteRepository(repository: NoteRepositoryImpl): NoteRepository
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): NoteDatabase {
        return Room.databaseBuilder(
            context = context.applicationContext,
            klass = NoteDatabase::class.java,
            name = "Notes.db"
        ).build()
    }

    @Provides
    fun provideNoteDao(database: NoteDatabase): NoteDao = database.noteDao()
}
