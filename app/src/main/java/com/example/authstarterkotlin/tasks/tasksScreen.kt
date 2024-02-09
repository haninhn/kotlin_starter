package com.example.authstarterkotlin.tasks

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

@Composable
fun TodoList() {
    var searchText by remember { mutableStateOf(TextFieldValue()) }
    var todos by remember { mutableStateOf(emptyList<Todo>()) }
    var filteredTodos by remember { mutableStateOf(emptyList<Todo>()) }

    LaunchedEffect(Unit) {
        todos = fetchTodos()
        filteredTodos = todos
    }

    TextField(

        value = searchText,
        onValueChange = { newSearchText ->
            searchText = newSearchText
            filteredTodos = filterTodos(todos, newSearchText.text)
        },
        label = { Text("Search" , color = Color.Black) },
        modifier = Modifier.fillMaxWidth()
    )


    Spacer(modifier = Modifier.height(16.dp))

    LazyColumn {
        items(filteredTodos) { todo ->
            TodoItem(todo)
        }
    }
}


suspend fun fetchTodos(): List<Todo> {
    return withContext(Dispatchers.IO) {
        val apiUrl = "https://dummyjson.com/todos"
        val todosJson = URL(apiUrl).readText()
        val todosResponse = Gson().fromJson(todosJson, TodosResponse::class.java)
        todosResponse.todos
    }
}

@Composable
fun TodoItem(todo: Todo) {
    Column(
        modifier = Modifier
            .padding(horizontal = 1.dp, vertical = 10.dp)
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color(0xFFEFC01B),
                shape = RoundedCornerShape(size = 14.dp),
            )
            .background(
                color = if (todo.completed) Color.White else Color(0xFFDCE9FF),
                shape = RoundedCornerShape(size = 14.dp)
            )
            .padding(vertical = 10.dp, horizontal = 10.dp)
    ) {
        Text(text = todo.todo, color = Color.Black)
        Text(text = "ID: ${todo.id}", color = Color.Black)
        Text(text = "Completed: ${if (todo.completed) "Yes" else "No"}", color = Color.Black)
        Text(text = "User ID: ${todo.userId}", color = Color.Black)
    }
}

fun filterTodos(todos: List<Todo>, query: String): List<Todo> {
    return todos.filter { it.todo.contains(query, ignoreCase = true) }
}
