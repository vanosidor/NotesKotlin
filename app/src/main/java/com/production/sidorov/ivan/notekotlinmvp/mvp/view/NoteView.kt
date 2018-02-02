package com.production.sidorov.ivan.notekotlinmvp.mvp.view

import com.arellomobile.mvp.MvpView
import com.production.sidorov.ivan.notekotlinmvp.mvp.model.Note


interface NoteView : MvpView{

    fun showMainView()

    fun setNoteView(note: Note)

    fun deleteNote()

}