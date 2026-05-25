package com.example.dessertclicker

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AppUiState())
    val uiState : StateFlow<AppUiState> = _uiState.asStateFlow()

    private val desserts : List<Dessert> = Datasource.dessertList

    init {
        val firstDessert = desserts.first()
        val firstDessertImageId = firstDessert.imageId
        val firstDessertPrice = firstDessert.price
        _uiState.update { state ->
            state.copy(
                currentDessertPrice = firstDessertPrice,
                currentDessertImageId = firstDessertImageId
            )
        }
    }
    fun updateRevenue() {
        val newRevenue = _uiState.value.revenue + _uiState.value.currentDessertPrice
        val newDessertSold = _uiState.value.dessertsSold + 1
        _uiState.update { state ->
            state.copy(
                revenue = newRevenue,
                dessertsSold = newDessertSold
            )
        }
    }

    fun determineDessertToShow(
        desserts: List<Dessert>,
        dessertsSold: Int
    ): Dessert {
        var dessertToShow = desserts.first()
        for (dessert in desserts) {
            if (dessertsSold >= dessert.startProductionAmount) {
                dessertToShow = dessert
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
                // you'll start producing more expensive desserts as determined by startProductionAmount
                // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
                // than the amount sold.
                break
            }
        }

        return dessertToShow
    }

    fun showNextDessert() {
        val dessertToShow = determineDessertToShow(desserts, _uiState.value.dessertsSold)
        _uiState.update { state ->
            state.copy(
                currentDessertImageId = dessertToShow.imageId,
                currentDessertPrice = dessertToShow.price
            )
        }
    }
}