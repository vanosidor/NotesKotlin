package com.production.sidorov.ivan.notekotlinmvp.ui.activity

import android.app.DialogFragment
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.production.sidorov.ivan.notekotlinmvp.R
import com.production.sidorov.ivan.notekotlinmvp.mvp.model.Note
import com.production.sidorov.ivan.notekotlinmvp.mvp.presenter.MainPresenter
import com.production.sidorov.ivan.notekotlinmvp.mvp.view.MainView
import com.production.sidorov.ivan.notekotlinmvp.ui.dialog.DeleteAllNotesDialogFragment
import com.production.sidorov.ivan.notekotlinmvp.ui.recycler.NotesAdapter
import kotlinx.android.synthetic.main.activity_main.*

const val INTENT_NOTE_ID = "note_id"

class MainActivity : MvpAppCompatActivity(), MainView, DeleteAllNotesDialogFragment.DeleteNotesDialogListener {
    @InjectPresenter
    lateinit var mainPresenter: MainPresenter

    private lateinit var notesAdapter: NotesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        fab.setOnClickListener({ mainPresenter.openNewNote(-1L) })

        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(this)

        notesAdapter = NotesAdapter(listener = { note -> mainPresenter.openNewNote(note.id) })

        recycler.adapter = notesAdapter

        mainPresenter.loadNotes()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId){
        R.id.item_menu_delete -> consume{mainPresenter.delete(notesAdapter.itemCount)}
        else -> false
    }

    private inline fun consume(f: () -> Unit): Boolean {
        f()
        return true
    }

    override fun showNotes(notes: List<Note>) {
        notesAdapter.setNotes(notes)
    }

    override fun showEmptyNotes() {
        emptyNotesText.visibility = View.VISIBLE
    }

    override fun showNoteView(noteId: Long) {
        val intent = Intent(this, NoteActivity::class.java)
        intent.putExtra(INTENT_NOTE_ID, noteId)
        startActivity(intent)
    }

    override fun deleteAllNotes() {
        notesAdapter.removeAllItems()
        emptyNotesText.visibility = View.VISIBLE
    }

    override fun showNotDeleteNotification() {
        Toast.makeText(this,"No items to delete", Toast.LENGTH_SHORT).show()
    }

    override fun showDialogDeleteAll(){
        val dialog = DeleteAllNotesDialogFragment()
        dialog.show(fragmentManager,"DeleteAllNotesDialog")
    }

    override fun onDialogPositiveClick(dialog: DialogFragment) {
        mainPresenter.deleteFromDatabase()

    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
    }

}
