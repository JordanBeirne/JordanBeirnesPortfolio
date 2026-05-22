/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.wcupa.jordanbeirnesportfolio.ui.project5.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import edu.wcupa.jordanbeirnesportfolio.R
import edu.wcupa.jordanbeirnesportfolio.ui.project5.data.local.UserPreferencesRepository
import edu.wcupa.jordanbeirnesportfolio.ui.project5.dataStore
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/*
 * View model of coffee Release components
 */
class CoffeeReleaseViewModel(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    val uiState: StateFlow<CoffeeReleaseUiState> =
        combine(
            userPreferencesRepository.isLinearLayout,
            userPreferencesRepository.favoriteCoffees
        ) { isLinear, favorites ->
            CoffeeReleaseUiState(
                isLinearLayout = isLinear,
                favorites = favorites
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = CoffeeReleaseUiState()
        )

    fun selectLayout(isLinearLayout: Boolean) {
        viewModelScope.launch {
            userPreferencesRepository.saveLayoutPreference(isLinearLayout)
        }
    }

    fun toggleFavorite(coffee: String) {
        viewModelScope.launch {
            userPreferencesRepository.toggleFavorite(coffee)
        }
    }

    companion object {
        fun provideFactory(context: Context): ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    val repo = UserPreferencesRepository(
                        context.applicationContext.dataStore
                    )

                    CoffeeReleaseViewModel(repo)
                }
            }
    }
}

/*
 * Data class containing various UI States for coffee Release screens
 */
data class CoffeeReleaseUiState(
    val isLinearLayout: Boolean = true,
    val favorites: Set<String> = emptySet(),
    val toggleContentDescription: Int =
        if (isLinearLayout) R.string.grid_layout_toggle else R.string.linear_layout_toggle,
    val toggleIcon: Int =
        if (isLinearLayout) R.drawable.ic_grid_layout else R.drawable.ic_linear_layout
)
