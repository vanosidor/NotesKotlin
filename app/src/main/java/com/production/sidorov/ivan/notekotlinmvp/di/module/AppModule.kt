package com.production.sidorov.ivan.notekotlinmvp.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.production.sidorov.ivan.notekotlinmvp.room.NoteDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module class AppModule(private val context: Context){

    @Provides
    @Singleton
    fun providesAppContext() = context

    @Provides
    @Singleton
    fun providesAppDatabase(context: Context) =
            Room.databaseBuilder(context,NoteDatabase::class.java,"note_database"
    ).build()

    @Provides
    @Singleton
    fun providesNoteDao(noteDatabase: NoteDatabase) = noteDatabase.noteDao()
}