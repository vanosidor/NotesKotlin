package com.production.sidorov.ivan.notekotlinmvp.ui.recycler

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.production.sidorov.ivan.notekotlinmvp.R
import com.production.sidorov.ivan.notekotlinmvp.mvp.model.Note
import com.production.sidorov.ivan.notekotlinmvp.ui.utils.DateConverter
import com.production.sidorov.ivan.notekotlinmvp.ui.utils.inflate
import kotlinx.android.synthetic.main.item_note.view.*

class NotesAdapter (private val listener: (Note) -> Unit) : RecyclerView.Adapter <NotesAdapter.NotesViewHolder>(){

    private var notes:List<Note> = ArrayList()

    override fun getItemCount() = notes.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder = NotesViewHolder(parent.inflate(R.layout.item_note))

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) = holder.bind(notes[position],listener)


    class NotesViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(note: Note,listener: (Note) -> Unit) = with(itemView){
            title.text = note.title
            text.text = note.text
            created_at.text = DateConverter.convert(note.createdDate)
            setOnClickListener { listener(note) }
        }
    }

    fun setNotes(data: List<Note>){
        notes = data
        notifyDataSetChanged()
    }

    fun removeAllItems(){
        notes = emptyList()
        notifyDataSetChanged()
    }

}