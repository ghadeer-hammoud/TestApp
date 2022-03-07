package com.example.testapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.LevelsUiState
import com.example.testapp.repositories.LevelsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class LevelsViewModel @Inject constructor(private val levelsRepository: LevelsRepository) : ViewModel() {

    private val _state = MutableStateFlow<LevelsUiState>(LevelsUiState.Loading())
    val state : StateFlow<LevelsUiState> get() = _state

    init {
        geLevels()
    }

    private fun geLevels(){
       viewModelScope.launch {
           levelsRepository.getLevels()
               .catch { exception -> _state.value = LevelsUiState.Error(exception) }
               .collect {
                   _state.value = LevelsUiState.Success(it)
               }
       }
    }
}