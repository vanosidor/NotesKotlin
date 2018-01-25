package com.production.sidorov.ivan.notekotlinmvp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.production.sidorov.ivan.notekotlinmvp.R
import com.production.sidorov.ivan.notekotlinmvp.mvp.model.Note
import com.production.sidorov.ivan.notekotlinmvp.mvp.presenter.MainPresenter
import com.production.sidorov.ivan.notekotlinmvp.mvp.view.MainView
import com.production.sidorov.ivan.notekotlinmvp.ui.recycler.NotesAdapter
import kotlinx.android.synthetic.main.activity_main.*

const val INTENT_NOTE_ID = "note_id"

class MainActivity : MvpAppCompatActivity(), MainView {


    @InjectPresenter
    lateinit var mainPresenter: MainPresenter

    lateinit var notesAdapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener({ mainPresenter.openNewNote(-1L) })

        recycler.layoutManager = LinearLayoutManager(this)

        notesAdapter = NotesAdapter(listener = { note -> mainPresenter.openNewNote(note.id) })

        recycler.adapter = notesAdapter

        mainPresenter.loadNotes()


    }

    override fun showNotes(notes: List<Note>) {
        notesAdapter.setNotes(notes)
    }

    override fun showEmptyNotes() {
        Toast.makeText(this, "There is no notes", Toast.LENGTH_LONG).show()
    }

    override fun showNoteView(noteId: Long) {
        val intent = Intent(this, NoteActivity::class.java)
        intent.putExtra(INTENT_NOTE_ID, noteId)
        startActivity(intent)
    }

}
