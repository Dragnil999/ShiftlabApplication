package com.example.shiftlabapplication.features.join.ui

import android.app.DatePickerDialog
import android.app.Dialog
import android.icu.util.GregorianCalendar
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import java.util.Calendar

class DateDialog : DialogFragment() {
    companion object {
        const val REQUEST_DATE = "dateRequest"
        const val BUNDLE_DATE = "dateBundle"

        fun newInstance(): DateDialog =
            DateDialog()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dateListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            val result = Bundle()
            result.putSerializable(BUNDLE_DATE, GregorianCalendar(year, month, dayOfMonth).time)
            setFragmentResult(REQUEST_DATE, result)
        }

        val calendar = Calendar.getInstance()
        val initialYear = calendar.get(Calendar.YEAR)
        val initialMonth = calendar.get(Calendar.MONTH)
        val initialDay = calendar.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(
            requireContext(),
            dateListener,
            initialYear,
            initialMonth,
            initialDay
        )
    }
}