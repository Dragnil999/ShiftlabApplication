package com.example.shiftlabapplication.features.start.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.shiftlabapplication.features.start.R
import com.example.shiftlabapplication.features.start.databinding.FragmentStartBinding
import com.example.shiftlabapplication.features.start.presentation.StartViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class StartFragment : Fragment() {
    companion object {
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
        setListeners()
    }

    private fun setListeners() {
        with(binding) {
            helloButton.setOnClickListener {

            }
        }
    }

}