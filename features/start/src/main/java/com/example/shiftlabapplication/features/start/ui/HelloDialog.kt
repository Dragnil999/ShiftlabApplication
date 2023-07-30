package com.example.shiftlabapplication.features.start.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class HelloDialog : DialogFragment() {
    private lateinit var binding: FragmentDialogHelloBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return requireContext().let {
            val builder = AlertDialog.Builder(it)

        }
    }
}