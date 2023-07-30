package com.example.shiftlabapplication.features.join.ui

import android.icu.text.DateFormat
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.shiftlabapplication.features.join.R
import com.example.shiftlabapplication.features.join.databinding.FragmentJoinBinding
import com.example.shiftlabapplication.features.join.presentation.JoinState
import com.example.shiftlabapplication.features.join.presentation.JoinViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Date

class JoinFragment : Fragment() {
    companion object {
        private const val DIALOG_DATE = "DateDialog"

        fun newInstance(): JoinFragment =
            JoinFragment()
    }

    private lateinit var binding: FragmentJoinBinding
    private val viewModel: JoinViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_join,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setObservers()
    }

    private fun setListeners() {
        with(binding) {
            nameField.doOnTextChanged { text, _, _, _ ->
                with(viewModel) {
                    userData = userData.copy(name = text.toString())
                    checkSummary()
                }
                /*viewModel.name = text.toString()
                viewModel.userData = viewModel.userData.copy(name = text.toString())
                viewModel.checkName()
                viewModel.checkSummary()*/
            }

            surnameField.doOnTextChanged { text, _, _, _ ->
                with(viewModel) {
                    userData = userData.copy(surname = text.toString())
                    checkSummary()
                }
                /*viewModel.surname = text.toString()
                viewModel.checkSurname()
                viewModel.checkSummary()*/
            }

            birthDateField.setOnClickListener {
                DateDialog.newInstance().apply {
                    this@JoinFragment.parentFragmentManager
                        .setFragmentResultListener(DateDialog.REQUEST_DATE, this) { requestKey, bundle ->
                            if (requestKey == DateDialog.REQUEST_DATE) {
                                val date = bundle.getSerializable(DateDialog.BUNDLE_DATE, Date::class.java) as Date
                                val str = DateFormat.getPatternInstance("dd.MM.yyyy").format(date)
                                birthDateField.setText(str)
                                with(viewModel) {
                                    userData = userData.copy(birthDate = date)
                                    checkSummary()
                                }
                                /*viewModel.birthDate = str
                                viewModel.checkSummary()*/
                            }
                        }
                    show(
                        this@JoinFragment.parentFragmentManager,
                        DIALOG_DATE
                    )
                }
            }

            passwordField.doOnTextChanged { text, _, _, _ ->
                with(viewModel) {
                    userData = userData.copy(password = text.toString())
                    checkSummary()
                }
                /*viewModel.password = text.toString()
                viewModel.checkPassword()
                viewModel.checkSummary()*/
            }

            repeatPasswordField.doOnTextChanged { text, _, _, _ ->
                viewModel.checkRepeatPassword(text.toString())
                viewModel.checkSummary()
            }

            joinButton.setOnClickListener {
                viewModel.sendUserName()
            }
        }
    }

    private fun setObservers() {
        viewModel.state.observe(viewLifecycleOwner, ::handleState)
    }

    private fun handleState(state: JoinState) {
        when (state) {
            is JoinState.Error.Name -> renderNameError()
            is JoinState.Error.Surname -> renderSurnameError()
            is JoinState.Error.BirthYear -> renderBirthYearError()
            is JoinState.Error.Password -> renderPasswordError()
            is JoinState.Error.RepeatPassword -> renderRepeatPasswordError()
            is JoinState.NoError -> renderRegistration()
            else -> renderInitial()
        }
    }

    private fun renderInitial() {
    }

    private fun renderRegistration() {
        with(binding) {
            joinButton.isEnabled = true
        }
    }

    private fun renderNameError() {
        with(binding) {
            joinButton.isEnabled = false
            nameField.error = getString(R.string.name_error)
        }
    }

    private fun renderSurnameError() {
        with(binding) {
            joinButton.isEnabled = false
            surnameField.error = getString(R.string.surname_error)
        }
    }

    private fun renderBirthYearError() {
        with(binding) {
            joinButton.isEnabled = false
            birthDateField.error = getString(R.string.birth_date_error)
        }
    }

    private fun renderPasswordError() {
        with(binding) {
            joinButton.isEnabled = false
            passwordField.error = getString(R.string.password_error)
        }
    }

    private fun renderRepeatPasswordError() {
        with(binding) {
            joinButton.isEnabled = false
            repeatPasswordField.error = getString(R.string.repeat_password_error)
        }
    }
}