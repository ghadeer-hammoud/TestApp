package com.example.testapp

import com.example.testapp.models.Level

sealed class LevelUiState{
    data class Success (val level: Level): LevelUiState()
    data class Error (val exception: Throwable): LevelUiState()
    data class Loading (val isLoading: Boolean = true): LevelUiState()
}