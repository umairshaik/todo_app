package com.example.todoapp.domain.repo

import com.example.todoapp.domain.TaskResponse
import com.example.todoapp.domain.service.TodoService
import com.example.todoapp.domain.service.entity.TaskEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TodoRepoImpl @Inject constructor(private val todoService: TodoService) : ITodoRepo {
    override fun getTaskList(): Flow<TaskResponse<List<TaskEntity>>> {

        println("int taskList repo")
        return flow { emit(safeApiCall { todoService.fetchTaskList() }) }
    }

    private suspend fun <T> safeApiCall(networkCall: suspend () -> T): TaskResponse<T> {
        return try {
            TaskResponse.Success<T>(networkCall.invoke())
        } catch (throwable: Throwable) {
            TaskResponse.Error<T>(throwable)
        }
    }
}