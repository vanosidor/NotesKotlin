package com.production.sidorov.ivan.notekotlinmvp.room

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.production.sidorov.ivan.notekotlinmvp.mvp.model.Note
import java.util.*

@Dao interface NoteDao{

    @Insert(onConflict = REPLACE)
    fun insertNote(note: Note)

    @Insert(onConflict = REPLACE)
    fun insertNotes(notes: List<Note>)

    @Query("SELECT * FROM notes WHERE id = :arg0 LIMIT 1")
    fun getNote(noteId: Long): Note

    @Query("select * from notes")
    fun getAllNotes():List<Note>

    @Query("UPDATE notes SET title = :arg1, text = :arg2, changed_at = :arg3 WHERE id = :arg0 ")
    fun updateNote(id: Long, title: String, text: String, modified: Date )

    @Delete
    fun deleteNote(note: Note)

    @Query("delete from notes")
    fun deleteAllNotes()

}
