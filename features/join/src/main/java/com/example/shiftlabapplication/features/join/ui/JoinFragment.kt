package com.example.shiftlabapplication.features.join.ui

import android.icu.text.DateFormat
import android.os.Bundle
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
import com.google.android.material.textfield.TextInputLayout
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
            }

            surnameField.doOnTextChanged { text, _, _, _ ->
                with(viewModel) {
                    userData = userData.copy(surname = text.toString())
                    checkSummary()
                }
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
                    checkRepeatPassword(repeatPasswordField.text.toString())
                    checkSummary()
                }
            }

            repeatPasswordField.doOnTextChanged { text, _, _, _ ->
                with(viewModel) {
                    checkRepeatPassword(text.toString())
                    checkSummary()
                }
            }

            joinButton.setOnClickListener {
                viewModel.sendUserData()
            }
        }
    }

    private fun setObservers() {
        viewModel.state.observe(viewLifecycleOwner, ::handleState)
    }

    private fun handleState(state: JoinState) {
        when (state) {
            is JoinState.Error.Name -> renderError(binding.nameInputLayout, R.string.name_error)
            is JoinState.Solved.Name -> renderErrorSolved(binding.nameInputLayout)
            is JoinState.Error.Surname -> renderError(binding.surnameInputLayout, R.string.surname_error)
            is JoinState.Solved.Surname -> renderErrorSolved(binding.surnameInputLayout)
            is JoinState.Error.BirthYear -> renderError(binding.birthDateInputLayout, R.string.birth_date_error)
            is JoinState.Solved.BirthDate -> renderErrorSolved(binding.birthDateInputLayout)
            is JoinState.Error.Password -> renderError(binding.passwordInputLayout, R.string.password_error)
            is JoinState.Solved.Password -> renderErrorSolved(binding.passwordInputLayout)
            is JoinState.Error.RepeatPassword -> renderError(binding.repeatPasswordInputLayout, R.string.repeat_password_error)
            is JoinState.Solved.RepeatPassword -> renderErrorSolved(binding.repeatPasswordInputLayout)
            is JoinState.NoError -> renderRegistration()
            else -> renderInitial()
        }
    }

    private fun renderInitial() {
        with(binding) {
            joinButton.isEnabled = false
        }
    }

    private fun renderRegistration() {
        with(binding) {
            joinButton.isEnabled = true
        }
    }

    private fun renderError(inputLayout: TextInputLayout, id: Int) {
        with(binding) {
            joinButton.isEnabled = false
            inputLayout.error = getString(id)
        }
    }

    private fun renderErrorSolved(inputLayout: TextInputLayout) {
        with(binding) {
            joinButton.isEnabled = false
            inputLayout.error = ""
        }
    }
}