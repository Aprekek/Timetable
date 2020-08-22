package ru.fevgenson.timetable.libraries.core.presentation.dialogs

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.io.Serializable

class NoticeDialogFragment<T : Serializable> : DialogFragment() {

    companion object {
        private const val TITLE = "title"
        private const val DESCRIPTION = "description"
        private const val CONFIRM_BUTTON_TEXT = "confirmButtonText"
        private const val CANCEL_BUTTON_TEXT = "cancelButtonText"
        private const val ACTION = "action"

        fun <T : Serializable> newInstance(
            title: Int,
            description: Int,
            confirmButtonText: Int,
            cancelButtonText: Int,
            action: T
        ): NoticeDialogFragment<T> = NoticeDialogFragment<T>().apply {
            arguments = Bundle().apply {
                putInt(TITLE, title)
                putInt(DESCRIPTION, description)
                putInt(CONFIRM_BUTTON_TEXT, confirmButtonText)
                putInt(CANCEL_BUTTON_TEXT, cancelButtonText)
                putSerializable(ACTION, action)
            }
        }
    }

    interface NoticeDialogListener<T : Serializable> {
        fun onDialogPositiveClick(action: T)
    }

    private lateinit var listener: NoticeDialogListener<T>
    private lateinit var title: String
    private lateinit var description: String
    private lateinit var confirmButtonText: String
    private lateinit var cancelButtonText: String
    private lateinit var action: T

    @Suppress("UNCHECKED_CAST")
    override fun onAttach(context: Context) {
        super.onAttach(context)
        with(
            requireNotNull(arguments) {
                "NoticeDialogFragment need to be created from newInstance static method"
            }
        ) {
            title = getString(getInt(TITLE))
            description = getString(getInt(DESCRIPTION))
            confirmButtonText = getString(getInt(CONFIRM_BUTTON_TEXT))
            cancelButtonText = getString(getInt(CANCEL_BUTTON_TEXT))
            action = getSerializable(ACTION) as T
        }
        listener = requireNotNull(targetFragment as? NoticeDialogListener<T>) {
            "$targetFragment + must implements NoticeDialogListener"
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        MaterialAlertDialogBuilder(requireContext()).apply {
            setTitle(title)
            setMessage(description)
            setPositiveButton(confirmButtonText) { _: DialogInterface, _: Int ->
                listener.onDialogPositiveClick(action)
            }
            setNegativeButton(cancelButtonText) { _: DialogInterface, _: Int -> }
        }.create()
}