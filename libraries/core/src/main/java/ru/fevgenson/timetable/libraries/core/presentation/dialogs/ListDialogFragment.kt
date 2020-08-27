package ru.fevgenson.timetable.libraries.core.presentation.dialogs

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ListDialogFragment : DialogFragment() {

    companion object {

        private const val TITLE = "TITLE"
        private const val LIST = "LIST"
        private const val CANCEL_BUTTON_TEXT = "CANCEL_BUTTON_TEXT"

        fun newInstance(
            title: Int,
            cancelButtonText: Int,
            list: List<String>
        ): ListDialogFragment = ListDialogFragment().apply {
            arguments = Bundle().apply {
                putInt(TITLE, title)
                putInt(CANCEL_BUTTON_TEXT, cancelButtonText)
                putStringArray(LIST, list.toTypedArray())
            }
        }
    }

    interface ListDialogListener {
        fun onItemSelected(item: String)
    }

    private lateinit var listener: ListDialogListener
    private lateinit var title: String
    private lateinit var cancelButtonText: String
    private lateinit var contentList: List<String>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        with(
            requireNotNull(arguments) {
                "ListDialogFragment need to be created from newInstance static method"
            }
        ) {
            title = getString(getInt(TITLE))
            cancelButtonText = getString(getInt(CANCEL_BUTTON_TEXT))
            contentList = requireNotNull(getStringArray(LIST)?.asList()) {
                "ListDialogFragment need to be created from newInstance static method"
            }
            listener = requireNotNull(targetFragment as? ListDialogListener) {
                "$targetFragment + ListDialogListener"
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        MaterialAlertDialogBuilder(requireContext()).apply {
            setTitle(title)
            setAdapter(
                ArrayAdapter(
                    context,
                    android.R.layout.simple_spinner_dropdown_item,
                    contentList
                )
            ) { dialogInterface: DialogInterface, position: Int ->
                listener.onItemSelected(contentList[position])
                dialogInterface.dismiss()
            }
            setNegativeButton(cancelButtonText) { _: DialogInterface, _: Int -> }
        }.create()
}