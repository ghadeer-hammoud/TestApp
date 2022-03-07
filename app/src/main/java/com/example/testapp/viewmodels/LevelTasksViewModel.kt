package com.example.testapp.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.LevelUiState
import com.example.testapp.LevelsUiState
import com.example.testapp.models.Level
import com.example.testapp.repositories.LevelsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class LevelTasksViewModel @Inject constructor(private val levelsRepository: LevelsRepository) : ViewModel() {

    private val _state = MutableStateFlow<LevelUiState>(LevelUiState.Loading())
    val state : StateFlow<LevelUiState> get() = _state

    public fun getLevelTasks(levelId: Int){
       viewModelScope.launch {
           val level = levelsRepository.getLevel(levelId)
           _state.value = LevelUiState.Success(level)
       }
    }

    public fun updateLevel(level: Level){
        viewModelScope.launch {
            levelsRepository.updateLevel(level)
            _state.value = LevelUiState.Success(level)
        }
    }

    public fun confirmLevel(level: Level){
        viewModelScope.launch {
            val a = levelsRepository.confirmLevel(level.order)
            val b = levelsRepository.unLockLevel(level.order+1)
        }
    }

}