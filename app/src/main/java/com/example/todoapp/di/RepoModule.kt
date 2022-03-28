package com.example.todoapp.di

import com.example.todoapp.domain.repo.ITodoRepo
import com.example.todoapp.domain.repo.TodoRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Singleton
    @Binds
    abstract fun bindTodoRepo(todoRepoImpl: TodoRepoImpl): ITodoRepo
}