package com.example.todoapp.di

import com.example.todoapp.domain.mapper.TaskListDomainMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainMapperModule {

    @Singleton
    @Provides
    fun bindTaskListDomainMapper(): TaskListDomainMapper {
        return TaskListDomainMapper()
    }
}