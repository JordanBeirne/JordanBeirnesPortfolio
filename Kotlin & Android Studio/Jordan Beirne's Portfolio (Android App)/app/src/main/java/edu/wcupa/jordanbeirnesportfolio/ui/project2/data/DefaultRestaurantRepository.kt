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
package edu.wcupa.jordanbeirnesportfolio.ui.project2.data

import kotlinx.coroutines.flow.Flow

class DefaultRestaurantRepository(
    private val dao: RestaurantDao
) : RestaurantRepository {

    override val restaurantsStream: Flow<List<Restaurant>> =
        dao.getAll()

    override fun getRestaurantStream(id: Long): Flow<Restaurant?> =
        dao.get(id)

    override suspend fun addRestaurant(restaurant: Restaurant) =
        dao.insert(restaurant)

    override suspend fun deleteRestaurant(restaurant: Restaurant) =
        dao.delete(restaurant)

    override suspend fun updateRestaurant(restaurant: Restaurant) =
        dao.update(restaurant)
}