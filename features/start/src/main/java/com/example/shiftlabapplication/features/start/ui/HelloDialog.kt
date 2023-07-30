package com.example.shiftlabapplication.features.start.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.example.shiftlabapplication.features.start.R
import com.example.shiftlabapplication.features.start.databinding.FragmentDialogHelloBinding
import com.example.shiftlabapplication.features.start.presentation.StartViewModel

class HelloDialog(private val viewModel: StartViewModel) : DialogFragment() {
    companion object {
        fun newInstance(viewModel: StartViewModel): HelloDialog =
            HelloDialog(viewModel)
    }

    private lateinit var binding: FragmentDialogHelloBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return requireContext().let {
            val builder = AlertDialog.Builder(it)
            binding = DataBindingUtil.inflate(
                requireActivity().layoutInflater,
                R.layout.fragment_dialog_hello,
                null,
                false
            )
            builder.setView(binding.root)
            getString(R.string.user_greetings_text, )
            binding.greetingsTextView.text = getString(R.string.user_greetings_text, viewModel.userName.name)
            builder.setTitle(R.string.dialog_title)
            builder.setPositiveButton(R.string.cancel_button) { dialog, _ ->
                dialog.dismiss()
            }

            builder.create()
        }
    }
}