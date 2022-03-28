package com.example.todoapp.di

import com.example.todoapp.domain.mapper.TaskListDomainMapper
import com.example.todoapp.domain.repo.ITodoRepo
import com.example.todoapp.domain.usecase.TaskListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TaskListUseCaseModule {

    @Singleton
    @Provides
    fun bindTaskListUseCase(
        repo: ITodoRepo,
        taskListDomainMapper: TaskListDomainMapper
    ): TaskListUseCase {
        return TaskListUseCase(repo, taskListDomainMapper)
    }
}