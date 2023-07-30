package com.example.shiftlabapplication.navigation.route

import com.example.shiftlabapplication.features.join.JoinScreen
import com.example.shiftlabapplication.features.start.navigation.StartNavigation
import com.github.terrakok.cicerone.Router

class StartNavigationImpl(private val router: Router) : StartNavigation {
    override fun goToJoinScreen() {
        router.newRootChain(JoinScreen.getJoinScreen())
    }
}