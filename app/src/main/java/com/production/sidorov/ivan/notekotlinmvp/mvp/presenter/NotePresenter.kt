package com.production.sidorov.ivan.notekotlinmvp.mvp.presenter

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.production.sidorov.ivan.notekotlinmvp.NoteApp
import com.production.sidorov.ivan.notekotlinmvp.mvp.model.Note
import com.production.sidorov.ivan.notekotlinmvp.mvp.view.NoteView
import com.production.sidorov.ivan.notekotlinmvp.room.NoteDao
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

@InjectViewState
class NotePresenter : MvpPresenter<NoteView>(){

    @Inject lateinit var noteDao: NoteDao

    init {
        NoteApp.component.inject(this)
    }

    fun saveNote(newNote: Note){

        Single.fromCallable{
            noteDao.insertNote(newNote)
        }.subscribeOn(Schedulers.io())
         .observeOn(AndroidSchedulers.mainThread())
         .subscribeBy(
                onError = {error->
                    Log.d("NotePresenter",error.message)
            })

        viewState.showMainView()
    }

    fun setNote(noteId: Long){
        if (noteId != -1L){
            Single.fromCallable {
                noteDao.getNote(noteId)
            }.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy (onSuccess = { note -> viewState.setNoteView(note)})
        }
    }

    fun updateNote(id: Long, title: String, text: String){
        Single.fromCallable {
            noteDao.updateNote(id,title,text,Calendar.getInstance().time)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess ={viewState.showMainView()},
                        onError = {throwable -> throwable.printStackTrace()})

    }

    fun deleteNote(noteId: Long){
        Single.fromCallable {
            noteDao.deleteNote(noteId)}
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                            onSuccess ={viewState.showMainView()},
                            onError = {throwable -> throwable.printStackTrace()})
        }

}
