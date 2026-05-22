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

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.wcupa.jordanbeirnesportfolio.R
import edu.wcupa.jordanbeirnesportfolio.ui.project5.data.local.LocalCoffeeReleaseData
import edu.wcupa.jordanbeirnesportfolio.ui.project5.ui.theme.CoffeeReleaseTheme
import edu.wcupa.jordanbeirnesportfolio.ui.project5.ui.theme.DarkBrown

/*
 * Screen level composable
 */
@Composable
fun CoffeeReleaseApp() {
    val context = LocalContext.current

    val coffeeReleaseViewModel: CoffeeReleaseViewModel = viewModel(
        factory = CoffeeReleaseViewModel.provideFactory(context)
    )

    CoffeeReleaseScreen(
        uiState = coffeeReleaseViewModel.uiState.collectAsState().value,
        selectLayout = coffeeReleaseViewModel::selectLayout,
        onToggleFavorite = coffeeReleaseViewModel::toggleFavorite
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CoffeeReleaseScreen(
    uiState: CoffeeReleaseUiState,
    selectLayout: (Boolean) -> Unit,
    onToggleFavorite: (String) -> Unit
) {
    val isLinearLayout = uiState.isLinearLayout
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.top_bar_name)) },
                actions = {
                    IconButton(
                        onClick = {
                            selectLayout(!isLinearLayout)
                        }
                    ) {
                        Icon(
                            painter = painterResource(uiState.toggleIcon),
                            contentDescription = stringResource(uiState.toggleContentDescription),
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = DarkBrown
                )
            )
        }
    ) { innerPadding ->
        val modifier = Modifier
            .padding(
                top = dimensionResource(R.dimen.padding_medium),
                start = dimensionResource(R.dimen.padding_medium),
                end = dimensionResource(R.dimen.padding_medium),
            )
        if (isLinearLayout) {
            CoffeeReleaseLinearLayout(
                favorites = uiState.favorites,
                onToggleFavorite = onToggleFavorite,
                modifier = modifier.fillMaxWidth(),
                contentPadding = innerPadding
            )
        } else {
            CoffeeReleaseGridLayout(
                favorites = uiState.favorites,
                onToggleFavorite = onToggleFavorite,
                modifier = modifier,
                contentPadding = innerPadding,
            )
        }
    }
}

@Composable
fun CoffeeReleaseLinearLayout(
    favorites: Set<String>,
    onToggleFavorite: (String) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
    ) {
        items(LocalCoffeeReleaseData.coffeeReleases) { coffee ->

            val isFavorite = favorites.contains(coffee)

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = if (isFavorite)
                        MaterialTheme.colorScheme.tertiary
                    else
                        MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.clickable {
                    onToggleFavorite(coffee)
                }
            ) {
                Text(
                    text = if (isFavorite) "⭐ $coffee" else coffee,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(R.dimen.padding_medium)),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun CoffeeReleaseGridLayout(
    favorites: Set<String>,
    onToggleFavorite: (String) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    val sortedList = LocalCoffeeReleaseData.coffeeReleases
        .sortedByDescending { favorites.contains(it) }
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(3),
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
    ) {
        items(sortedList) { coffee ->

            val isFavorite = favorites.contains(coffee)

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = if (isFavorite)
                        MaterialTheme.colorScheme.tertiary
                    else
                        MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier
                    .height(110.dp)
                    .clickable { onToggleFavorite(coffee) },
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    text = if (isFavorite) "⭐ $coffee" else coffee,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxHeight()
                        .wrapContentHeight(Alignment.CenterVertically)
                        .padding(dimensionResource(R.dimen.padding_small))
                        .align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CoffeeReleaseLinearLayoutPreview() {
    CoffeeReleaseTheme {
        CoffeeReleaseLinearLayout(
            favorites = emptySet(),
            onToggleFavorite = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CoffeeReleaseGridLayoutPreview() {
    CoffeeReleaseTheme {
        CoffeeReleaseGridLayout(
            favorites = emptySet(),
            onToggleFavorite = {}
        )
    }
}

@Preview
@Composable
fun CoffeeReleaseAppPreview() {
    CoffeeReleaseTheme {
        CoffeeReleaseLinearLayout(
            favorites = emptySet(),
            onToggleFavorite = {}
        )
    }
}
