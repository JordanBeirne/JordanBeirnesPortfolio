package edu.wcupa.jordanbeirnesportfolio.ui.project2.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.wcupa.jordanbeirnesportfolio.ui.project2.data.RestaurantRepository
import edu.wcupa.jordanbeirnesportfolio.ui.project2.data.Restaurant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class RestaurantListViewModel(
    private val repository: RestaurantRepository
) : ViewModel() {

    val restaurantsStream: Flow<List<Restaurant>> =
        repository.restaurantsStream

    fun deleteRestaurant(restaurant: Restaurant) =
        viewModelScope.launch {
            repository.deleteRestaurant(restaurant)
        }
}