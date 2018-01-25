package com.production.sidorov.ivan.notekotlinmvp.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.production.sidorov.ivan.notekotlinmvp.mvp.model.Note

@Database(entities = [(Note::class)],version = 1,exportSchema = false)
@TypeConverters(DateConverter :: class)
abstract class NoteDatabase : RoomDatabase(){
    abstract fun noteDao(): NoteDao
}
