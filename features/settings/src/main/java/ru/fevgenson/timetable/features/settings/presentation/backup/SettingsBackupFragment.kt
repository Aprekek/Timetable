package ru.fevgenson.timetable.features.settings.presentation.backup

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.fevgenson.timetable.features.settings.R
import ru.fevgenson.timetable.features.settings.databinding.FragmentSettingsBackupBinding

class SettingsBackupFragment : Fragment(), SettingsBackupViewModel.EventListener {

    companion object {

        const val READ_REQUEST_CODE = 12
        const val CREATE_REQUEST_CODE = 11
        const val BACKUP_MIME_TYPE = "application/octet-stream"
    }

    private lateinit var binding: FragmentSettingsBackupBinding
    private val viewModel: SettingsBackupViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initBinding(layoutInflater, container)
        initViewModel()
        return binding.root
    }

    private fun initViewModel() {
        viewModel.eventsDispatcher.observe(viewLifecycleOwner, this)
    }

    private fun initBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ) {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_settings_backup,
            container,
            false
        )
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
    }

    override fun navigateBack() {
        findNavController().popBackStack()
    }

    override fun openBackupFile() {
        val intent = Intent().apply {
            action = Intent.ACTION_OPEN_DOCUMENT
            addCategory(Intent.CATEGORY_OPENABLE)
            type = BACKUP_MIME_TYPE
        }
        startActivityForResult(intent, READ_REQUEST_CODE)
    }

    override fun createBackupFile(backupFileName: String) {
        val intent = Intent().apply {
            action = Intent.ACTION_CREATE_DOCUMENT
            addCategory(Intent.CATEGORY_OPENABLE)
            type = BACKUP_MIME_TYPE
            putExtra(Intent.EXTRA_TITLE, backupFileName)
        }
        startActivityForResult(intent, CREATE_REQUEST_CODE)
    }

    override fun recreate() {
        requireActivity().recreate()
    }

    override fun showToast(textRes: Int) {
        Toast.makeText(requireActivity().applicationContext, textRes, Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        val result = data?.data
        if (resultCode == Activity.RESULT_OK && result != null) {
            when (requestCode) {
                CREATE_REQUEST_CODE -> viewModel.setCreatedFile(result)
                READ_REQUEST_CODE -> viewModel.setOpenedFile(result)
            }
        }
    }
}