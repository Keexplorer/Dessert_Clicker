package com.example.dessertclicker

data class AppUiState(
    val revenue : Int = 0,
    val dessertsSold : Int = 0,
    val currentDessertIndex : Int = 0,
    val currentDessertPrice : Int = 0,
    val currentDessertImageId : Int = 0
)