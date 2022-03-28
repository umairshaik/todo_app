package com.example.todoapp.domain.usecase

import com.example.todoapp.domain.DomainWrapper
import com.example.todoapp.domain.entity.TaskDomain
import com.example.todoapp.domain.mapToDomainWrapper
import com.example.todoapp.domain.mapper.TaskListDomainMapper
import com.example.todoapp.domain.repo.ITodoRepo
import kotlinx.coroutines.flow.single

class TaskListUseCase(
    private val repo: ITodoRepo,
    private val taskListDomainMapper: TaskListDomainMapper
) {
    suspend fun getTaskList(): DomainWrapper<List<TaskDomain>> {
        return repo.getTaskList().single().mapToDomainWrapper(taskListDomainMapper)
    }
}