package com.example.shiftlabapplication.navigation

import com.example.shiftlabapplication.features.join.JoinScreen
import com.example.shiftlabapplication.features.start.StartScreen
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

fun buildCicerone(): Cicerone<Router> =
    Cicerone.create().apply {
        router.newRootScreen(JoinScreen.getJoinScreen())
    }