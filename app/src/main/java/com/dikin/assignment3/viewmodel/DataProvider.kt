package com.dikin.assignment3.viewmodel

import com.dikin.assignment3.lazycolumn.Task

object DataProvider {
    private var _id = 0
    val taskList = mutableListOf(
        Task(++_id, "Create a compose", "Creating a composable function"),
        Task(++_id, "Create a project", "Creating a Android Studio project", _isDone = true),
        Task(++_id, "Create a LazyColumn", "Creating a compose with LazyColumn"),
        Task(
            ++_id,
            "Create a data class",
            "Creating a Task data class for LazyColumn",
            _isDone = true
        ),
    )

    val id get() = ++_id

    fun addTask(title: String, description: String) {
        val newTask = Task(++_id, title, description)
        taskList.add(newTask)
    }
}