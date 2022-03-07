package com.example.testapp

import com.example.testapp.models.Level

sealed class LevelsUiState{
    data class Success (val tasksList: List<Level>): LevelsUiState()
    data class Error (val exception: Throwable): LevelsUiState()
    data class Loading (val isLoading: Boolean = true): LevelsUiState()
}