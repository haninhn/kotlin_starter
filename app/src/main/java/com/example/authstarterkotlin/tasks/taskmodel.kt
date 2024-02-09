package com.example.authstarterkotlin.tasks

data class Todo(
    val id: Int,
    val todo: String,
    val completed: Boolean,
    val userId: Int
)

data class TodosResponse(
    val todos: List<Todo>,
    val total: Int,
    val skip: Int,
    val limit: Int
)
