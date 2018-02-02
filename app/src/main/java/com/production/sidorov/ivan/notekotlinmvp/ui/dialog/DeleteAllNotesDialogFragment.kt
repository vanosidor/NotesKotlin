package com.production.sidorov.ivan.notekotlinmvp.ui.dialog

import android.app.Dialog
import android.app.DialogFragment
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AlertDialog

class DeleteAllNotesDialogFragment : DialogFragment() {

    lateinit var onDialogDeleteNotesListener: DeleteNotesDialogListener

    interface DeleteNotesDialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            onDialogDeleteNotesListener = context as DeleteNotesDialogListener
        }
        catch (ex: ClassCastException){ throw ClassCastException(context.toString() + " must implement DialogClickListener")}
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        builder.apply {
            setMessage("Do you really want to delete all notes?")
            setPositiveButton("DELETE"){ _, _ ->
                onDialogDeleteNotesListener.onDialogPositiveClick(dialog = this@DeleteAllNotesDialogFragment)}
            setNegativeButton("CANCEL"){ _, _ ->
                onDialogDeleteNotesListener.onDialogNegativeClick(dialog = this@DeleteAllNotesDialogFragment)  }
        }
        return builder.create()
    }
}