package edu.wcupa.jordanbeirnesportfolio.ui.project5

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

private const val LAYOUT_PREFERENCE_NAME = "layout_preferences"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = LAYOUT_PREFERENCE_NAME
)