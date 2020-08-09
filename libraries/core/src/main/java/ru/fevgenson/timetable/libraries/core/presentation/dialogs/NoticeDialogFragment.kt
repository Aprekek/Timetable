package ru.fevgenson.timetable.libraries.core.presentation.dialogs

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class NoticeDialogFragment : DialogFragment() {
    interface NoticeDialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }

    private lateinit var listener: NoticeDialogListener
    private var title: String? = null
    private var description: String? = null
    private var confirmButtonText: String? = null
    private var cancelButtonText: String? = null

    fun initialize(
        _title: String,
        _description: String,
        _cancelButtonText: String = "Отмена",
        _confirmButtonText: String = "Подтвердить"
    ) {
        title = _title
        description = _description
        cancelButtonText = _cancelButtonText
        confirmButtonText = _confirmButtonText
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = targetFragment as NoticeDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context + must implements NoticeDialogListener")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = MaterialAlertDialogBuilder(requireContext())

        if (savedInstanceState?.isEmpty == false) {
            title = savedInstanceState.getString("title")
            description = savedInstanceState.getString("description")
            confirmButtonText = savedInstanceState.getString("confirmButtonText")
            cancelButtonText = savedInstanceState.getString("cancelButtonText")
        }

        builder.setTitle(title).setMessage(description).setPositiveButton(confirmButtonText)
        { _: DialogInterface, _: Int ->
            listener.onDialogPositiveClick(this)
        }.setNegativeButton(cancelButtonText)
        { _: DialogInterface, _: Int ->
            listener.onDialogNegativeClick(this)
        }

        return builder.create()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("title", title)
        outState.putString("description", description)
        outState.putString("confirmButtonText", confirmButtonText)
        outState.putString("cancelButtonText", cancelButtonText)

        super.onSaveInstanceState(outState)
    }
}