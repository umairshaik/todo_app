package com.example.todoapp.domain.mapper

import com.example.todoapp.domain.entity.TaskDomain
import com.example.todoapp.domain.service.entity.TaskEntity

class TaskListDomainMapper : IDomainMapper<List<TaskEntity>, List<TaskDomain>> {
    override fun mapToDomainModel(sourceModel: List<TaskEntity>): List<TaskDomain> {
        return sourceModel.map {
            TaskDomain(
                id = it.id,
                description = it.description,
                scheduledDate = it.scheduledDate,
                status = it.status == "COMPLETED"
            )
        }
    }

    override fun mapFromDomainModel(domainModel: List<TaskDomain>): List<TaskEntity> {
        return domainModel.map {
            TaskEntity(
                id = it.id,
                description = it.description,
                scheduledDate = it.scheduledDate,
                status = if (it.status) "COMPLETED" else "PENDING"
            )
        }
    }

}