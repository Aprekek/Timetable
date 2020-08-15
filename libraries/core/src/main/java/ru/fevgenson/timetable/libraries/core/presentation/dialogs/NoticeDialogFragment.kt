package ru.fevgenson.timetable.libraries.core.presentation.dialogs

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class NoticeDialogFragment : DialogFragment() {

    private companion object {
        const val TITLE = "title"
        const val DESCRIPTION = "description"
        const val CONFIRM_BUTTON_TEXT = "confirmButtonText"
        const val CANCEL_BUTTON_TEXT = "cancelButtonText"
        const val ACTION = "action"
    }

    interface NoticeDialogListener {
        fun onDialogPositiveClick(action: Int)
    }

    private lateinit var listener: NoticeDialogListener
    private var title: String? = null
    private var description: String? = null
    private var confirmButtonText: String? = null
    private var cancelButtonText: String? = null
    private var action: Int? = null

    fun initialize(
        _title: String,
        _description: String,
        _cancelButtonText: String = "",
        _confirmButtonText: String = "",
        _action: Int
    ) {
        title = _title
        description = _description
        cancelButtonText = _cancelButtonText
        confirmButtonText = _confirmButtonText
        action = _action
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = targetFragment as NoticeDialogListener
        } catch (e: ClassCastException) {
            Log.d(
                "NoticeDialogFragment",
                "$targetFragment + must implements NoticeDialogListener"
            )
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = MaterialAlertDialogBuilder(requireContext())

        with(savedInstanceState)
        {
            this?.let {
                if (!isEmpty) {
                    title = getString(TITLE)
                    description = getString(DESCRIPTION)
                    confirmButtonText = getString(CONFIRM_BUTTON_TEXT)
                    cancelButtonText = getString(CANCEL_BUTTON_TEXT)
                    action = getInt(ACTION)
                }
            }
        }

        builder.setTitle(title).setMessage(description)
            .setPositiveButton(confirmButtonText) { _: DialogInterface, _: Int ->
                action?.let { listener.onDialogPositiveClick(it) }
            }.setNegativeButton(cancelButtonText) { _: DialogInterface, _: Int -> }

        return builder.create()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(TITLE, title)
        outState.putString(DESCRIPTION, description)
        outState.putString(CONFIRM_BUTTON_TEXT, confirmButtonText)
        outState.putString(CANCEL_BUTTON_TEXT, cancelButtonText)
        outState.putInt(
            ACTION,
            action ?: throw NullPointerException("\"action\" must be not null")
        )

        super.onSaveInstanceState(outState)
    }
}