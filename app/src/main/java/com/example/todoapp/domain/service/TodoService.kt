package com.example.todoapp.domain.service

import com.example.todoapp.domain.service.entity.TaskEntity
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface TodoService {
    @GET("v2/5a8e5b372f000048004f25fc")
    suspend fun fetchTaskList(): List<TaskEntity>

    companion object {
        private const val BASE_URL = "http://www.mocky.io/"

        fun getRetrofitInstance(): TodoService {
            val logger =
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)

                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TodoService::class.java)
        }
    }
}