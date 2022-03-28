package com.example.todoapp.domain.service.entity

data class TaskEntity(
    val id: Int,
    val description: String,
    val scheduledDate: String,
    var status: String
)
