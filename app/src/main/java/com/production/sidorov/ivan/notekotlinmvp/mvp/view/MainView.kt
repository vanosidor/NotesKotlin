package com.production.sidorov.ivan.notekotlinmvp.mvp.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.production.sidorov.ivan.notekotlinmvp.mvp.model.Note

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface MainView : MvpView{

    fun showNotes(notes: List<Note>)

    fun showEmptyNotes()

    fun showNoteView(noteId: Long)

    fun deleteAllNotes()

    @StateStrategyType(value = SkipStrategy::class)
    fun showDialogDeleteAll()

    fun showNotDeleteNotification()
}
