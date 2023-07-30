package com.example.shiftlabapplication.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import org.koin.dsl.module
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "userData")
private val NAME_KEY = stringPreferencesKey("name_key")

val dataModule = module {
    single { get<Context>().dataStore }
    single { NAME_KEY }
}