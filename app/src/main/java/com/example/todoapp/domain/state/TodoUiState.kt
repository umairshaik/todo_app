package com.example.todoapp.domain.state

import com.example.todoapp.domain.entity.TaskDomain
import com.example.todoapp.domain.service.entity.UiError

interface TodoUiState<T> {
    val data: T
    val error: UiError?
    val loading: Boolean
}

data class TaskListUiState(
    override val data: List<TaskDomain> = emptyList(),
    override val error: UiError? = null,
    override val loading: Boolean = false

) : TodoUiState<List<TaskDomain>>

fun TaskListUiState.loading() = this.copy(loading = true)

fun TaskListUiState.success(taskList: List<TaskDomain>) =
    this.copy(data = taskList, error = null, loading = false)

fun TaskListUiState.onError(uiError: UiError) = this.copy(error = uiError)