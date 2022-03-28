package com.example.todoapp.ui.today

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.domain.DomainWrapper
import com.example.todoapp.domain.entity.TaskDomain
import com.example.todoapp.domain.state.TaskListUiState
import com.example.todoapp.domain.usecase.TaskListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(

    private val taskListUseCase: TaskListUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<TaskListUiState>(TaskListUiState())

    val uiState: StateFlow<TaskListUiState> = _uiState.asStateFlow()
    fun getAllTask() = viewModelScope.launch {
        when (val taskList = taskListUseCase.getTaskList()) {
            is DomainWrapper.Success -> {
                _uiState.getAndUpdate { uiState.value.copy(data = taskList.data, loading = false) }
            }
            is DomainWrapper.Error -> {
                _uiState.getAndUpdate { uiState.value.copy(error = taskList.uiError) }
            }
        }
    }

    fun updateTask(taskDomain: TaskDomain) {
       uiState.value.data.filter { it -> it.id == taskDomain.id }.map {
           it.status = taskDomain.status
       }
        _uiState.getAndUpdate { uiState.value.copy()  }
        viewModelScope.launch {

            Log.i("TAG in _ui", _uiState.last().data.toString())
            Log.i("TAG in ui", uiState.last().data.toString())
        }
    }
}