package com.production.sidorov.ivan.notekotlinmvp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.production.sidorov.ivan.notekotlinmvp.R
import com.production.sidorov.ivan.notekotlinmvp.mvp.model.Note
import com.production.sidorov.ivan.notekotlinmvp.mvp.presenter.NotePresenter
import com.production.sidorov.ivan.notekotlinmvp.mvp.view.NoteView
import kotlinx.android.synthetic.main.activity_note.*
import java.util.*

class NoteActivity : MvpAppCompatActivity(), NoteView {


    @InjectPresenter
    lateinit var notePresenter: NotePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        val noteId = intent.getLongExtra(INTENT_NOTE_ID, -1)

        notePresenter.setNote(noteId)

        button_save.setOnClickListener {

            val title: String = title_et.text.toString()
            val text: String = text_et.text.toString()

            if (noteId != -1L) {
                notePresenter.updateNote(noteId, title, text)
            } else {
                val newNote = Note()
                newNote.title = title
                newNote.text = text
                notePresenter.saveNote(newNote)
            }
        }
    }

    override fun showMainView() {
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun setNoteView(note: Note) {
        title_et.setText(note.title)
        text_et.setText(note.text)
    }
}
