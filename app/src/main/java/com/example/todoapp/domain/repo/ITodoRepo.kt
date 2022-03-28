package com.example.todoapp.domain.repo

import com.example.todoapp.domain.TaskResponse
import com.example.todoapp.domain.service.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

interface ITodoRepo {
    fun getTaskList(): Flow<TaskResponse<List<TaskEntity>>>
}