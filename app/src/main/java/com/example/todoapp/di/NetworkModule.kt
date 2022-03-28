package com.example.todoapp.di

import com.example.todoapp.domain.service.TodoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideTodoServiceInstance(): TodoService {
        return TodoService.getRetrofitInstance()
    }
}