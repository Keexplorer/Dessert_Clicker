package com.example.dessertclicker

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DessertViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AppUiState())
    val uiState : StateFlow<AppUiState> = _uiState.asStateFlow()
}