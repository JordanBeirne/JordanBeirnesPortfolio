package edu.wcupa.jordanbeirnesportfolio.ui.project2.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import android.content.Context
import edu.wcupa.jordanbeirnesportfolio.ui.project2.data.AppDataContainer

object AppViewModelProvider {
    private var container: AppDataContainer? = null

    private fun getContainer(context: Context): AppDataContainer {
        if (container == null) {
            container = AppDataContainer(context.applicationContext)
        }
        return container!!
    }
    val Factory = viewModelFactory {

        initializer {
            val context = this[AndroidViewModelFactory.APPLICATION_KEY]!!.applicationContext
            val container = getContainer(context)

            RestaurantEntryViewModel(container.trackerRepository)
        }

        initializer {
            val context = this[AndroidViewModelFactory.APPLICATION_KEY]!!.applicationContext
            val container = getContainer(context)

            RestaurantListViewModel(container.trackerRepository)
        }
    }
}

