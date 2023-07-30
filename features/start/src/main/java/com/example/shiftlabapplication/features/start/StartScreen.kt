package com.example.shiftlabapplication.features.start

import com.example.shiftlabapplication.features.start.ui.StartFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object StartScreen {
    fun getStartScreen() = FragmentScreen {
        StartFragment.newInstance()
    }
}