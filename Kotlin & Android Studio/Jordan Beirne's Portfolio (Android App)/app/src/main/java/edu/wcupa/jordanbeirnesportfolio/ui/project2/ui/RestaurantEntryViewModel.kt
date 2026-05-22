package edu.wcupa.jordanbeirnesportfolio.ui.project2.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.wcupa.jordanbeirnesportfolio.ui.project2.data.Restaurant
import edu.wcupa.jordanbeirnesportfolio.ui.project2.data.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class RestaurantEntryViewModel(
    private val repository: RestaurantRepository
) : ViewModel() {

    fun getRestaurantStream(id: Long): Flow<Restaurant?> =
        repository.getRestaurantStream(id)

    fun saveRestaurant(
        id: Long,
        name: String,
        description: String,
        cuisine: String,
        rating: Int
    ) {
        val restaurant = Restaurant(id, name, description, cuisine, rating)
        viewModelScope.launch {
            if (id > 0) {
                repository.updateRestaurant(restaurant)
            } else {
                repository.addRestaurant(restaurant)
            }
        }
    }
}