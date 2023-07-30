package com.example.shiftlabapplication.features.start.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.shiftlabapplication.features.start.R
import com.example.shiftlabapplication.features.start.databinding.FragmentStartBinding
import com.example.shiftlabapplication.features.start.presentation.StartState
import com.example.shiftlabapplication.features.start.presentation.StartViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class StartFragment : Fragment() {
    companion object {
        private const val DIALOG_HELLO = "HelloDialog"

        fun newInstance(): StartFragment =
            StartFragment()
    }

    private lateinit var binding: FragmentStartBinding
    private val viewModel: StartViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_start,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUserName()
        setListeners()
        setObservers()
    }

    private fun setListeners() {
        with(binding) {
            helloButton.setOnClickListener {
                HelloDialog.newInstance(viewModel).apply {
                    show(this@StartFragment.parentFragmentManager, DIALOG_HELLO)
                }
            }
        }
    }

    private fun setObservers() {
        viewModel.state.observe(viewLifecycleOwner, ::handleState)
    }

    private fun handleState(state: StartState) {
        when (state) {
            is StartState.Loading -> renderLoading()
            else -> renderHelloButton()
        }
    }

    private fun renderLoading() {
        with(binding) {
            progressBar.visibility = View.VISIBLE
            helloButton.visibility = View.GONE
        }
    }

    private fun renderHelloButton() {
        with(binding) {
            progressBar.visibility = View.GONE
            helloButton.visibility = View.VISIBLE
        }
    }

}