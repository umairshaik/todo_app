package com.example.todoapp.domain

sealed class TaskResponse<Response> {
    data class Error<Response>(val throwable: Throwable) : TaskResponse<Response>()
    data class Success<Response>(val data: Response) : TaskResponse<Response>()
}