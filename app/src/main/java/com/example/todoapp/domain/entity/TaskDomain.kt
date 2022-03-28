package com.example.todoapp.domain.entity

data class TaskDomain(
    val id: Int,
    val description: String,
    val scheduledDate: String,
    var status: Boolean
)
