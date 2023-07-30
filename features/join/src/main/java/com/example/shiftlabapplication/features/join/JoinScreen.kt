package com.example.shiftlabapplication.features.join

import com.example.shiftlabapplication.features.join.ui.JoinFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object JoinScreen {
    fun getJoinScreen() = FragmentScreen {
        JoinFragment.newInstance()
    }
}