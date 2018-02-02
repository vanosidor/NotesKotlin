package com.production.sidorov.ivan.notekotlinmvp.mvp.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.production.sidorov.ivan.notekotlinmvp.NoteApp
import com.production.sidorov.ivan.notekotlinmvp.mvp.model.Note
import com.production.sidorov.ivan.notekotlinmvp.mvp.view.MainView
import com.production.sidorov.ivan.notekotlinmvp.room.NoteDao
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
class MainPresenter @Inject constructor()   : MvpPresenter<MainView>() {

    @Inject lateinit var noteDao: NoteDao

    init {
        NoteApp.component.inject(this)
    }

    fun loadNotes(){

       Single.fromCallable{
            noteDao.getAllNotes()

        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = {
                            notes -> showNotes(notes)},
                        onError = {
                            error -> error.printStackTrace()})
    }

    fun openNewNote(id: Long){
        viewState.showNoteView(id)
    }

    fun delete(itemCount: Int){
        if(itemCount==0) viewState.showNotDeleteNotification()
        else viewState.showDialogDeleteAll()
    }

    fun deleteFromDatabase(){
        Single.fromCallable { noteDao.deleteAllNotes() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy (onSuccess = {viewState.deleteAllNotes()})
        viewState.deleteAllNotes()
    }

    private fun showNotes(notes: List<Note> ){
        if ( notes.isEmpty()) viewState.showEmptyNotes()
        else viewState.showNotes(notes)
    }
}