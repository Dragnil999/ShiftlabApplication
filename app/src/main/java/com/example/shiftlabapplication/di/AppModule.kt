package com.example.shiftlabapplication.di

import com.example.shiftlabapplication.features.join.navigation.JoinNavigation
import com.example.shiftlabapplication.features.start.navigation.StartNavigation
import com.example.shiftlabapplication.navigation.buildCicerone
import com.example.shiftlabapplication.navigation.route.JoinNavigationImpl
import com.example.shiftlabapplication.navigation.route.StartNavigationImpl
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import org.koin.dsl.module

val appModule = module {
    single { buildCicerone() }
    single { get<Cicerone<Router>>().router }
    single { get<Cicerone<Router>>().getNavigatorHolder() }

    factory<StartNavigation> { StartNavigationImpl(router = get()) }
    factory<JoinNavigation> { JoinNavigationImpl(router = get()) }
}