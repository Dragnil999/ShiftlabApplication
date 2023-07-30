package com.example.shiftlabapplication.navigation.route

import com.example.shiftlabapplication.features.join.navigation.JoinNavigation
import com.example.shiftlabapplication.features.start.StartScreen
import com.github.terrakok.cicerone.Router

class JoinNavigationImpl(private val router: Router) : JoinNavigation {
    override fun goToStartScreen() {
        router.newRootScreen(StartScreen.getStartScreen())
    }
}